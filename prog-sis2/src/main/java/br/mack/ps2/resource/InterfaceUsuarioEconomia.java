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