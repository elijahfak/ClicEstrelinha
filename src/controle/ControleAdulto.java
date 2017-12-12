package controle;

import excecoes.AdultoNaoEncontradoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import modelo.Adulto;

public class ControleAdulto implements Serializable {

    private HashMap<Integer, Adulto> pessoas;

    public static ControleAdulto instancia;

    public static ControleAdulto getInstance() {
        //return GerrenciadorLivrosHolder.INSTANCE;
        if (instancia == null) {
            ControleAdulto gu = new ControleAdulto();
            instancia = gu;
        }

        return instancia;
    }

    public void escreveAdultosTeste() {
        Iterator it = this.getPessoas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Adulto adulto = (Adulto) pair.getValue();

            System.out.println(adulto.getCpf() + " - " + adulto.getNome());
        }
    }

    public static void setInstance(ControleAdulto cf) {
        instancia = cf;
    }

    public ControleAdulto() {
        this.pessoas = new HashMap<Integer, Adulto>();
    }

    boolean isCpfJaCadastrado(int cpf) {
        return this.getPessoas().containsKey(cpf);
    }

    public HashMap<Integer, Adulto> getPessoas() {
        return pessoas;
    }

    public void setPessoas(HashMap<Integer, Adulto> pessoas) {
        this.pessoas = pessoas;
    }

    void addResponsavel(String nome, int cpf_int, int telefone, String email, int sexo) {
        Adulto adulto = new Adulto(
                cpf_int, email, telefone, nome, sexo, true
        );

        this.getPessoas().put(cpf_int, adulto);

        Serializacao.getInstance().gravarAdultos(ControleAdulto.getInstance());
    }

    public void editarResponsavel(Adulto adulto) {
        this.getPessoas().put(adulto.getCpf(), adulto);
        Serializacao.getInstance().gravarAdultos(ControleAdulto.getInstance());
    }

    public ArrayList<Adulto> buscar_adulto() {
        ArrayList<Adulto> result_set = new ArrayList<Adulto>();

        Iterator it = this.getPessoas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Adulto adulto = (Adulto) pair.getValue();
            result_set.add(adulto);
        }

        return result_set;
    }

    public Adulto buscar_adulto_indice(int index) {
        if (this.getPessoas().size() < 1) {
            return null;
        }

//        if (this.getPessoas().size() - 1 > index) {
//            return null;
//        }
        int i = 0;
        Adulto adulto = null;
        Iterator it = this.getPessoas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Adulto ad = (Adulto) pair.getValue();

            System.out.println(ad.getCpf());

            if (index == i) {
                adulto = ad;
                break;
            }

            i++;
        }

        return adulto;
    }

    public Adulto buscar_adulto_cpf(int cpf) throws AdultoNaoEncontradoException {
        if (!this.getPessoas().containsKey(cpf)) {
            throw new AdultoNaoEncontradoException("Responsável não encontrado");
        }

        Adulto ad = ad = this.getPessoas().get(cpf);

        System.out.println(ad);

        return ad;
    }

    public String[][] getAdulto_Matriz() {
        String[][] retorno = new String[this.getPessoas().size()][2];

        int i = 0;
        Iterator it = this.getPessoas().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Adulto adulto = (Adulto) pair.getValue();
            retorno[i][0] = adulto.getNome();
            retorno[i][1] = adulto.getCpf() + "";

            i++;
        }

        return retorno;
    }

    public String[] getAdultoStringArray() {
        ArrayList<Adulto> func_set = this.buscar_adulto();

        int list_size = func_set.size();
        String[] list_set = new String[list_size];

        for (int i = 0; i < list_size; i++) {
            list_set[i] = func_set.get(i).getNome();
        }
        return list_set;
    }

}
