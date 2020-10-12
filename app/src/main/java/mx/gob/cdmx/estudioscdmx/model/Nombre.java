package mx.gob.cdmx.estudioscdmx.model;

public class Nombre {

	static final public String USUARIO = "usuario";
	static final public String ENTREVISTA = "entrevista";
	static final public String DENUNCIA = "denuncia";
	static final public String LISTADENUNCIAS = "listasdenuncias";
	public static final String FOLIO = "folio";
	public static final String EMAIL = "email";
	public static final String USER_ID = "userId";
	public static final String TOKEN = "token";
	public static final String NOMBRE = "nombre";
	public static final String TIPO_USUARIO = "tipoUsuario";
	public static final String APLICACION = "aplicacion";

	static final public String URL = "https://opinion.cdmx.gob.mx/cgi-bin/php/geo/mapa_ssc.php?cuadrante=";
	public String nombreEncuesta(){
		
		final String nombreEncuesta = "encuestas";


		return nombreEncuesta;
	}
}	 