package scrapt;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.ApiRest.model.DatosSismos;
import java.util.ArrayList;

@Service
public class scrapting {
	public static int eliminarDecimal(double num) {
		String Num = num+"";
		int numero = Num.indexOf(".");
		return Integer.parseInt(Num.substring(0,numero));
	}
	public static Double eliminarLetra(String letra) {
		Double d = 0.0;
		letra = letra.replaceAll("[^\\d.-]", "");
		d = (letra!=null) ? Double.valueOf(letra) : 0.0;
		return d;
	}
	public static List<DatosSismos> extraerDatos() throws IOException, ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		int profundidad;
		double doble;
		String magnitud;
		java.util.Date fecha;
		Document doc = Jsoup.connect("http://www.sismologia.cl/links/ultimos_sismos.html").timeout(6000).get();
		Element body = doc.select("tbody").first();
		List<DatosSismos> Datos = new ArrayList<>();
		int i = 0;
		int o = 0;
		for(Element tr: body.select("tr")) {
			if(o>0) {
				DatosSismos sismito = new DatosSismos();
				for(Element td: tr.select("td")) {
					if(i == 0) {
						fecha = formato.parse(td.text());
			            sismito.setFechaLocal(fecha);
					}
					if(i == 1) {
			            fecha = formato.parse(td.text());
			            
			            sismito.setFechaUTC(fecha);
					}
					if(i == 2) {
			            doble = Double.parseDouble(td.text());
			            sismito.setLatitud(doble);
					}
					if(i == 3) {
			            doble = Double.parseDouble(td.text());
			            sismito.setLongitud(doble);
					}
					if(i == 4) {
			            doble = Double.parseDouble(td.text());
			            profundidad = eliminarDecimal(doble);
			            sismito.setProfundidad(profundidad);
					}
					if(i == 5) {
			            magnitud = td.text();
			            doble = eliminarLetra(magnitud);
			            sismito.setMagnitud(doble);
					}
					if(i == 7) {
			            sismito.setReferencia_Geografica(td.text());
					}
					i++;
				}
				sismito.setIdd(o);
				Datos.add(sismito);
				i=0;
				o++;
			}
			else {
				o++;
			}
		}
		return Datos;
    }
}
