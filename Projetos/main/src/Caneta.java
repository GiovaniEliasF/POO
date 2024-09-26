public class Caneta{
    private String modelo;
    private String cor;
    private float ponta;
    private int carga;
    private boolean tampada;

    public Caneta () {

    }
    
    public Caneta (String modelo, String cor, float ponta, int carga, boolean tampada){
        this.modelo = modelo;
        this.cor = cor;
        this.ponta = ponta;
        this.carga = carga;
        this.tampada = tampada;
    }

    

    public void status(){
        System.out.println("Modelo = " + modelo);
        System.out.println("Cor = " + cor);
        System.out.println("Ponta = " + ponta);
        System.out.println("Carga = " + carga);
        if (this.tampada){
            System.out.println("Esta Tampada.");
        } else {
            System.out.println("Nao esta tampada");
        }
    }

    public void escrever(){
        if(this.tampada){
            System.out.println("Voce nao pode escrever, a caneta esta tampada");
        } else {
            System.out.println("Escrevendo...");
            this.carga -= 5;
        }
    }

    public void tampar(){
        if (this.tampada){
            System.out.println("A Caneta ja esta tampada");
        } else {
            System.out.println("Tampando a Caneta...");
            this.tampada = true;
        }
    }

    public void destampar(){
        if (this.tampada){
            System.out.println("Destampando a Caneta...");
            this.tampada = false;
        } else {
            System.out.println("A Caneta ja esta destampada");
        }
    }

    public boolean isTampada(){
        return this.tampada;
    }

}    