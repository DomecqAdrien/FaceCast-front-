package kagura.project.com.facecast2.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kagura.project.com.facecast2.R;
import kagura.project.com.facecast2.objects.Evenement;
import kagura.project.com.facecast2.objects.Flux;


public class FluxAdapter extends ArrayAdapter<Evenement> {

	private Activity activity;
	private List<Evenement> items;
	private int row;

	public FluxAdapter(Activity act, int resource, ArrayList<Evenement> arrayList) {
		super(act, resource, arrayList);
		this.activity = act;
		this.row = resource;
		this.items = arrayList;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		Evenement objBean = items.get(position);

		holder.nom = (TextView) view.findViewById(R.id.nom);
		holder.adresse = (TextView) view.findViewById(R.id.adresse);		

		if (holder.nom != null && null != objBean.getNom()
				&& objBean.getNom().trim().length() > 0) {
			holder.nom.setText(Html.fromHtml(objBean.getNom()));
		}
		

				
		return view;
	}

	private class ViewHolder {
		TextView nom, adresse;
	}
}