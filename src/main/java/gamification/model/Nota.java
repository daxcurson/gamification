package gamification.model;

public enum Nota 
{
	BIEN("BIEN","Bien"),
	MAL("MAL","Mal"),
	REGULAR("REGULAR","Regular");
	
	private String nota_valor;
	private String nota_nombre;
	
	private Nota(String nota_valor,String nota_nombre)
	{
		this.nota_valor=nota_valor;
		this.nota_nombre=nota_nombre;
	}
	public String getNota_valor()
	{
		return this.nota_valor;
	}
	public String getNota_nombre()
	{
		return this.nota_nombre;
	}
}
