// 17/03/2023
// Program by Guilherme Malgarizi Vasquez
import java.util.Scanner;
import java.util.HashMap;
import java.util.concurrent.*;

public class Extra 
{
    private static Cofrinho cofrinho;
    private static Scanner sc;

    public static void main(String[]args) throws InterruptedException
    {
        sc = new Scanner(System.in);
        term_init();
        cofrinho = new Cofrinho();
        menu();
    }

    public static void term_init() throws InterruptedException
    {
        char[] bar = new char[100];
        for(int i = 0; i < bar.length; i++)
        {
            bar[i] = ' ';
        }

        for(int i = 0; i <= bar.length; i++)
        {
            String aux = "[";
            for(int j = 0; j < bar.length; j++)
            {
                aux += bar[j];
            }
            aux += "]";
            if(i < bar.length)
            {
                aux += " carregando...\r";
            }
            else
            {
                aux += " finalizado :D\r";
            }
            System.out.print(aux);

            if(i == bar.length)
            {
                bar[i-1] = '=';
            }
            else
            {
                bar[i] = '>';
                if(i - 1 >= 0)
                {
                    bar[i-1] = '=';
                }            
            }
            
            TimeUnit.MILLISECONDS.sleep(20);
        }

        String bv = "\nBem Vindo ao $ Cofrinho $\nVamos comecar\n";
        for(int i = 0; i < bv.length(); i++)
        {
            System.out.print(bv.charAt(i));
            TimeUnit.MILLISECONDS.sleep(45);
        }
    }
    
    public static void menu()
    {
        HashMap<Integer, NomeMoeda> nome = new HashMap<Integer, NomeMoeda>();
        nome.put(1, NomeMoeda.UmReal);
        nome.put(2, NomeMoeda.Cinquenta);
        nome.put(3, NomeMoeda.VinteCinco);
        nome.put(4, NomeMoeda.Dez);
        nome.put(5, NomeMoeda.Cinco);
        nome.put(6, NomeMoeda.Um);

        while(true)
        {
            System.out.println("1 - Inserir moedas\n2 - Retirar moedas\n3 - Sair");
            System.out.println("O que deseja fazer?");
            int in = Integer.parseInt(sc.nextLine());
            switch(in)
            {
                case 1:
                    System.out.println("Qual moeda deseja adicionar");
                    System.out.println("1 - R$1\n2 - R$0.50\n3 - R$0.25\n4 - R$0.10\n5 - R$0.05\n6 - R$0.01");
                    int j = Integer.parseInt(sc.nextLine());
                    if(cofrinho.insere(new Moeda(nome.get(j))))
                    {
                        System.out.println("Moeda inserida");
                    }
                    break;
                case 2:
                    if(cofrinho.retira() != null)
                    {
                        System.out.println("Moeda retirada");
                    }
                    else
                    {
                        System.out.println("Cofrinho está vazio");
                    }
                    break;
                case 3:
                    System.exit(0);
            }
            
            System.out.println(cofrinho.getQtdadeMoedas() + " moedas no cofrinho");
            System.out.println(cofrinho.getQtdadeMoedasTipo(NomeMoeda.UmReal) + " moedas de R$1 no cofrinho");
            System.out.println(cofrinho.getQtdadeMoedasTipo(NomeMoeda.Cinquenta) + " moedas de R$0.50 no cofrinho");
            System.out.println("Tem " + cofrinho.getValorTotalCentavos() + " centavos no cofrinho");
            System.out.println("Tem R$" + cofrinho.getValorTotalReais() + " no cofrinho");
            int size = cofrinho.getQtdadeMoedas();
            if(size != 0)
            {
                if(size != 1)
                {
                    if(size != 2)
                    {
                        System.out.println("Teria "+relF()+" centavos apos retirar as ultimas duas moedas");
                    }
                    else
                    {
                        System.out.println("Teria 0 centavos no cofrinho apos retirar as unicas duas moedas nele");
                    }
                }
                else
                {
                    System.out.println("Apenas uma moeda no cofrinho, so conseguimos retirar uma e nao duas. Teria 0 centavos no cofrinho");
                }
            }
            else
            {
                System.out.println("Cofrinho esta vazio, nao tem duas moedas para retirar");
            }
        }
    }

    public static int getTamanho()
    {
        try
        {
            System.out.println("Quantas moedas o cofrinho consegue armazenar:");
            double in = Double.parseDouble(sc.nextLine());
            if(isInt(in))
            {
                return (int)in;
            }
            else
            {
                System.out.println("Digite um novo numero INT");
                double a = getTamanho();
                return (int)a;
            }
        }
        catch(Exception e)
        {
            System.out.println("Parece que algo deu errado ne");
            return getTamanho();
        }
    }

    public static boolean isInt(double a)
    {
        int i = 0;
        if(a % 1 != 0)
        {
            System.out.println("Faz sentido ter decimais? Nao, entao sem numeros decimais");
            i++;
        }
        if(a < 0)
        {
            System.out.println("Nao faz sentido ser negativo. Que tal usar um INT positivo? Acho que dai funciona");
            i++;
        }
        if(i > 0)
        {
            return false;
        }
        return true;
    }

    public static int relF()
    {
        Moeda retira1 = cofrinho.retira();
        Moeda retira2 = cofrinho.retira();
        int cent = cofrinho.getValorTotalCentavos();
        cofrinho.insere(retira2);
        cofrinho.insere(retira1);
        return cent;
    }
}