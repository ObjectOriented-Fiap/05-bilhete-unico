import java.util.Random;

public class BilheteUnico {

    //atributos
    static final double TARIFA = 5.20;
    long numero;
    double saldo;
    Usuario usuario;//variável de referencia, endereço de memória

    //construtor
    public BilheteUnico(String nome, long cpf, String perfil){
        Random random = new Random();
        numero = random.nextLong(1000,10000);
        usuario = new Usuario(nome, cpf, perfil);
    }

    //metodo para carregar o bilhete
    public double carregar(double valor) {
        saldo += valor;
        return saldo;
    }

    //metodo para consultar o saldo
    public double consultarSaldo(){
        return saldo;
    }

    //metodo para passar na catraca
    public String passarNaCatraca(){
        double debito = TARIFA / 2;
        if (usuario.perfil.equalsIgnoreCase("comum")){
            debito = TARIFA;
        }
        if (saldo >= debito){
            saldo -= debito;
            return "Catraca liberada";
        }
        return "Catraca bloqueada, saldo insuficiente";
    }

}
