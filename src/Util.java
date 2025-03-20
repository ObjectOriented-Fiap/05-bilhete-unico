import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    //banco de dados
    private BilheteUnico[] bilhete = new BilheteUnico[5];//bilhete com alocação estatica e sequencial
    private int index =0;

    //metodo do menu principal
    public void menuPrincipal(){
        int opcao;
        String menu = "1. Admin \n 2. Usuário \n 3.Finalizar";

        do {
            opcao = parseInt(showInputDialog(menu));
            switch (opcao){
                case 1:
                    menuAdmin();
                    break;
                case 2:
                    break;
            }
        }while (opcao != 3);

    }

    //metodo menu secundario
    private void menuAdmin(){
        int opcao;

        //String menu = "1. Emitir bilhete \n 2. Listar bilhetes \n 3. Excluir bilhete \n 4. Sair";
        String menu = "MENU ADMINISTRADOR \n";
        menu += "1. Emitir bilhete \n";
        menu += "2. Listar bilhete \n";
        menu += "3. Excluir bilhete \n";
        menu += "4. Sair";

        do {
            opcao = parseInt(showInputDialog(menu));
            switch (opcao){
                case 1:
                    emitirBilhete();
                    break;
                case 2:
                    listarBilhetes();
                    break;
                case 3:
                    excluirBilhete();
                    break;
            }
        }while (opcao != 4);
    }

    //metodo para emitir o bilhete
    private void emitirBilhete(){
        String nome,perfil;
        long cpf;
        if (index < bilhete.length){
            nome = showInputDialog("Nome do usuário: ");
            cpf = parseLong(showInputDialog("CPF: "));
            perfil = showInputDialog("Estudante ou Professor ou Comum:");
            bilhete[index] = new BilheteUnico(nome,cpf,perfil);
            index++;//pode substituir na linha de cima como bilhete[index++] = ...
        }
        else {
            showMessageDialog(null,"Procure um posto de atendimento");
        }
    }

    //metodo para listar os bilhetes
    private void listarBilhetes(){
        DecimalFormat df = new DecimalFormat(" 0.00");
        String aux = "LISTA DE BILHETES \n \n";
        for (int i =0; i < index;i++){
            aux += "Usuário: "+ bilhete[i].usuario.nome +"\n";
            aux += "CPF: "+ bilhete[i].usuario.cpf +"\n";
            aux += "Perfil: "+ bilhete[i].usuario.perfil +"\n";
            aux += "Número do bilhete: "+ bilhete[i].numero +"\n";
            aux += "Saldo do bilhete: R$ "+ df.format(bilhete[i].saldo) +"\n \n";
        }
        showMessageDialog(null,aux);
    }

    //metodo para excluir o bilhete
    private void excluirBilhete(){

    }
}
