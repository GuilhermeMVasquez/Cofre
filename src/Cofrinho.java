public class Cofrinho
{
    private Moeda[] cofrinho;
    private int next;

    public Cofrinho()
    {
        next = 0;
        cofrinho = new Moeda[Extra.getTamanho()];
    }

    public boolean insere(Moeda moeda)
    {
        if(next < cofrinho.length)
        {
            cofrinho[next] = moeda;
            next++;
            return true;
        }
        System.out.println("Cofrinho cheio");
        return false;
    }

    public Moeda retira()
    {
        if(next > 0)
        {
            Moeda aux = cofrinho[next-1];
            cofrinho[next-1] = null;
            next--;
            return aux;
        }
        return null;
    }

    public int getQtdadeMoedas()
    {
        return next;
    }

    public int getQtdadeMoedasTipo(NomeMoeda nomeMoeda)
    {
        int qt = 0;
        for(int i = 0; i < next; i++)
        {
            if(cofrinho[i].getNomeMoeda() == nomeMoeda)
            {
                qt++;
            }
        }
        return qt;
    }

    public int getValorTotalCentavos()
    {
        int cents = 0;
        for(int i = 0; i < next; i++)
        {
            cents += cofrinho[i].getValorCentavos();
        }
        return cents;
    }

    public double getValorTotalReais()
    {
        int tmp = getValorTotalCentavos();
        double reais = tmp / 100.;
        return reais;
    }
}