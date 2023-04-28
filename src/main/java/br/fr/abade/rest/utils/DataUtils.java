package br.fr.abade.rest.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DataUtils {

	//metodo para pegar uma data do calendario passando por parametro o dia, inserido data futura ou passada
	public static String getDataDiferencaDias(Integer qtdDias) {
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, qtdDias);
		return getDataFormatada(calendario.getTime());
	}
	
	public static String getDataFormatada(Date data) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(data);
	}
}
