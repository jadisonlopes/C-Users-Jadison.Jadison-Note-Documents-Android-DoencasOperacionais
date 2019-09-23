package com.newproject.doencasoperacionais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lopes on 06/05/2018.
 */

public class Adapter extends ArrayAdapter<Object> {

    private final Context context;
    private final ArrayList<Object> elementos;

    public Adapter(Context context, ArrayList<Object> elementos) {
        super(context, R.layout.row_adapter, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_adapter,parent, false);

        TextView Doe    = (TextView) rowView.findViewById(R.id.idAText1);
        TextView Sint    = (TextView) rowView.findViewById(R.id.idAText3);
        TextView Trat    = (TextView) rowView.findViewById(R.id.idAText2);
        TextView Func    = (TextView) rowView.findViewById(R.id.idAText4);
        TextView FuncAux = (TextView) rowView.findViewById(R.id.idATextAux4);
        TextView Desc    = (TextView) rowView.findViewById(R.id.idAText);

        Doe.setText(elementos.get(position).getDoenca());
        Desc.setText(elementos.get(position).getDescricao());
        Sint.setText(elementos.get(position).getSintomas());
        Trat.setText(elementos.get(position).getTratamento());
        if (!elementos.get(position).getFuncao().equals("")){
            Func.setVisibility(View.VISIBLE);
            FuncAux.setVisibility(View.VISIBLE);
            Func.setText(elementos.get(position).getFuncao());
        }else{
            Func.setVisibility(View.GONE);
            FuncAux.setVisibility(View.GONE);
        }

        return rowView;
    }
}
