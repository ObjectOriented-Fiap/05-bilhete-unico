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
            if (opcao < 1 || opcao > 3){
                showMessageDialog(null,"Opção inválida");
            }else {
                switch (opcao) {
                    case 1:
                        menuAdmin();
                        break;
                    case 2:
                        menuUsuario();
                        break;
                }
            }
        }while (opcao != 3);

    }

    // ------------------------------MENU ADMINISTRADOR ---------------------------------

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
            if (opcao < 1 || opcao > 4){
                showMessageDialog(null,"Opção inválida");
            }else {
                switch (opcao) {
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
        if (index == 0){
            showMessageDialog(null, "Não há bilhetes cadastrados");
        }else{
            String aux = "LISTA DE BILHETES \n \n";
        for (int i =0; i < index;i++){
            aux += "Usuário: "+ bilhete[i].usuario.nome +"\n";
            aux += "CPF: "+ bilhete[i].usuario.cpf +"\n";
            aux += "Perfil: "+ bilhete[i].usuario.perfil +"\n";
            aux += "Número do bilhete: "+ bilhete[i].numero +"\n";
            aux += "Saldo do bilhete: R$ "+ df.format(bilhete[i].saldo) +"\n \n";
        }
            showMessageDialog(null, aux);
        }
    }

    //metodo para excluir o bilhete
    private void excluirBilhete(){
        int resposta;
        int indice = pesquisar();
        if (indice != -1){
            resposta = showInternalConfirmDialog(null,"Tem certeza que deseja excluir?");
            if (resposta == YES_OPTION){
                bilhete[indice] = bilhete[index-1];
                index--;
            }
        }
    }

    //-------------------------------MENU USUARIO-----------------------------------------------


    //menu Secundario
    private void menuUsuario(){
        int opcao;

        //String menu = "1. Emitir bilhete \n 2. Listar bilhetes \n 3. Excluir bilhete \n 4. Sair";
        String menu = "MENU USUARIO \n";
        menu += "1. Carregar bilhete \n";
        menu += "2. Consultar saldo \n";
        menu += "3. Passar na catraca \n";
        menu += "4. Sair";

        do {
            opcao = parseInt(showInputDialog(menu));
            if (opcao < 1 || opcao > 4){
                showMessageDialog(null,"Opção inválida");
            }else {
                switch (opcao) {
                    case 1:
                        carregarBilhete();
                        break;
                    case 2:
                        consultarSaldo();
                        break;
                    case 3:
                        passarCatraca();
                        break;
                }
            }
        }while (opcao != 4);
    }

    private void carregarBilhete(){
        int indice = pesquisar();
        double valor;
        if (indice != -1){
           valor = parseDouble(showInputDialog("Valor da recarga: "));
           bilhete[indice].carregar(valor);
        }
    }

    private void consultarSaldo(){
        DecimalFormat df = new DecimalFormat(" 0.00");
        int indice = pesquisar();
        if (indice != -1){
            showMessageDialog(null, "Saldo = R$ " + bilhete[indice].consultarSaldo());
        }
    }

    private void passarCatraca(){
        int indice = pesquisar();
        if (indice != -1){
            showMessageDialog(null, bilhete[indice].passarNaCatraca());
        }
    }

    private int pesquisar(){
        long cpf = parseLong(showInputDialog("Digite o CPF:"));
        for (int i = 0; i < index; i++) {
            if (bilhete[i].usuario.cpf == cpf) {
                return i;
            }
        }
        showMessageDialog(null, cpf + " não encontrado");
        return -1;
    }

}
