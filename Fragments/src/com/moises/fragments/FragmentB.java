package com.moises.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentB extends Fragment implements OnItemClickListener{

	View v;
	private ListView lvListaDatos;
	
	PersonaSeleccionadoClickListener listener;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		v = inflater.inflate(R.layout.fragment_b, container, false);
		iniciarComponentes(v);
		return v;
	}
	
	public void iniciarComponentes(View v){
		lvListaDatos = (ListView)v.findViewById(R.id.lvListaDatos);
		lvListaDatos.setOnItemClickListener(this);
		ArrayAdapter<Persona> adapter = new ArrayAdapter<Persona>(getActivity(), android.R.layout.simple_expandable_list_item_1, getListaPersonas());
		lvListaDatos.setAdapter(adapter);
	}
	
	public ArrayList<Persona> getListaPersonas(){
		ArrayList<Persona> lista_datos = new ArrayList<Persona>();
		for(int i=1000; i<1010; i++){
			Persona per = new Persona(i, "Persona "+i);
			lista_datos.add(per);
		}
		return lista_datos;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		Persona per = (Persona)parent.getAdapter().getItem(position);
		Intent intent = new Intent(getActivity().getApplicationContext(), SecondActivity.class);
		intent.putExtra("per", per);
		startActivity(intent);
//		listener.seleccionadoClick(per);
//		try {
//			this.finalize();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
	}
	
	public interface PersonaSeleccionadoClickListener{
		public void seleccionadoClick(Persona per);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try {
			listener = (PersonaSeleccionadoClickListener)activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(e.toString()+" Debe implementar PersonaSeleccionadoClickListener");
		}
	}

}
