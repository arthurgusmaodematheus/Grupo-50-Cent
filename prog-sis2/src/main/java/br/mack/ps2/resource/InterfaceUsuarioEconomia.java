package br.mack.ps2.resource;

import br.mack.ps2.api.Economia;
import br.mack.ps2.dao.EconomiaDao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InterfaceUsuarioEconomia {
    EconomiaDao economiaDAO;
    Scanner in;

    public InterfaceUsuarioEconomia(EconomiaDao economiaDAO) {
        this.economiaDAO = economiaDAO;
        this.in = new Scanner(System.in);

    }

    public InterfaceUsuarioEconomia(){

    }

    public void iniciar() {
        imprimirMenu();
    }

    private void imprimirMenu() {
        int opc = 0;
        while (true) {
            System.out.println("\n============");
            System.out.println("====Menu====");
            System.out.println("============");
            System.out.println("\t1. Criar");
            System.out.println("\t2. Ler");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Deletar");
            System.out.println("\t5. Sair");
            opc = in.nextInt();

            in.nextLine();

            switch (opc) {
                case 1:
                    this.createEconomia();
                    break;
                case 2:
                    this.readEconomia();
                    break;
                case 3:
                    this.updateEconomia();
                    break;
                case 4:
                    this.deleteEconomia();
                    break;
                case 5:
                    System.out.println("Tchau!");
                    return;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
    }

    private void createEconomia() {
        Economia economia = new Economia();

        System.out.println("\n***********************");
        System.out.println("*** Novo Dado ***");
        System.out.println("***********************");
        System.out.println("\nInforme a data da informação: ");
        try {
            Scanner s = new Scanner(System.in);
            String dataRecebida = s.nextLine();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = df.parse(dataRecebida);
            economia.setDate(dt);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        System.out.println("Informe o valor do dia:  ");
        economia.setValue(in.nextInt());


        if (economiaDAO.create(economia)) {
            System.out.println("Dados adicionados ao banco de Dados");
        } else {
            System.out.println("Ops: problema ao adicionar dados");
        }
    }
    private void readEconomia() {
        List<Economia> economias = economiaDAO.read();

        System.out.println("\n*******************************************");
        System.out.println("**** Lista dos dados cadastrados ****");
        System.out.println("********************************************");
        for (Economia economia : economias) {
            System.out.println("Id da data: " + economia.getId());
            System.out.println("Data do dado: " + economia.getDate());
            System.out.println("Valor do dia: " + economia.getValue() + "\n");

        }
    }


    private void updateEconomia() {
        Economia economia = new Economia();

        System.out.println("*** Atualizar uma data ***");
        System.out.println("Insira o ID da data que deseja modificar: ");

        economia.setId(in.nextInt());
        in.nextLine();

        try {
            Scanner s = new Scanner(System.in);
            System.out.println("Altere a data do valor: ");
            String dataRecebida = s.nextLine();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dt = df.parse(dataRecebida);
            economia.setDate(dt);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        System.out.println("Altere o valor do dia: ");
        economia.setValue(in.nextInt());

        if (economiaDAO.update(economia)) {
            System.out.println("Dado atualizado no Banco de Dados");
        } else {
            System.out.println("Ops: problema ao adicionar dado");
        }
    }


    private void deleteEconomia() {
        List<Economia> economias = economiaDAO.read();


        while (true) {
            System.out.println("\n******************************************");
            System.out.println("*** Lista dos dias cadastrados: ***");
            System.out.println("******************************************");

            System.out.println(economias);

            int a = 0;
            for (Economia economia : economias) {
                System.out.println(a + ". Id do dia: " + economia.getId());
                System.out.println("  Data: " + economia.getDate());
                System.out.println("  Valor: " + economia.getValue());

                a++;

            }
            System.out.println(a + ". Cancelar a operação");

            System.out.println("Qual dia deseja remover?\n");
            int resposta = in.nextInt();
            in.nextLine();

            if (resposta == a) {
                break;
            } else if (resposta > economias.size() || resposta < 0) {
                System.out.println("Está opção não é válida");
            } else if (economiaDAO.delete(economias.get(resposta))) {
                System.out.println("Dia: " + economias.get(resposta).getDate() + " removido com sucesso");
            } else {
                System.out.println("Ops: Falha ao tentar remover!");
            }
            break;
        }
    }
}