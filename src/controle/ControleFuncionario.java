/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import excecoes.FuncionarioSemPermissaoException;
import excecoes.FuncionarioNaoEncontradoException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import modelo.Funcionario;
import excecoes.*;
import java.util.ArrayList;
import java.util.Iterator;
import modelo.FuncionarioInterface;

public class ControleFuncionario implements Serializable {
    
    private HashMap<String, FuncionarioInterface> funcionarios;
    private int qtd_funcionario;
    private int sequence_funcionario;
    private FuncionarioInterface funcionario_sessao;
    public static ControleFuncionario instancia;
    
    public static ControleFuncionario getInstance() {
        //return GerrenciadorLivrosHolder.INSTANCE;
        if (instancia == null) {
            ControleFuncionario gu = new ControleFuncionario();
            instancia = gu;
        }
        
        return instancia;
    }
    
    public static void setInstance(ControleFuncionario cf) {
        instancia = cf;
    }
    
    public ControleFuncionario() {
        this.funcionarios = new HashMap<String, FuncionarioInterface>();
        this.funcionario_sessao = null;
    }
    
    public int getQtd_funcionario() {
        return qtd_funcionario;
    }
    
    public void setQtd_funcionario(int qtd_funcionario) {
        this.qtd_funcionario = qtd_funcionario;
    }
    
    public FuncionarioInterface getFuncionario_sessao() {
        return funcionario_sessao;
    }
    
    public void setFuncionario_sessao(Funcionario funcionario_sessao) {
        this.funcionario_sessao = funcionario_sessao;
    }
    
    void addFuncionario(Funcionario func) {
//        func.setData_demissao(func.getData_admissao());
        System.out.println(" - Novo " + func.getCargo() + " Adicionado\n" + func.toString());
        this.getFuncionarios().put(func.getMatricula(), func);
        
        sequence_funcionario++;
        qtd_funcionario++;
        
        Serializacao.getInstance().gravarFuncionarios(ControleFuncionario.getInstance());
    }
    
    void editarFuncionario(FuncionarioInterface funcionario) {
        this.getFuncionarios().put(funcionario.getMatricula(), funcionario);
        Serializacao.getInstance().gravarFuncionarios(ControleFuncionario.getInstance());
    }
    
    boolean cpfExiste(String cpf) {
        String cpf_ = cpf.replace(".", "");
        cpf_ = cpf_.replace("-", "");
        int int_cpf = Integer.parseInt(cpf_);
        return this.cpfExiste(int_cpf);
    }
    
    public HashMap<String, FuncionarioInterface> getFuncionarios() {
        return funcionarios;
    }
    
    public void setFuncionarios(HashMap<String, FuncionarioInterface> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
    public String getSequenceComplementoMat() {
        // return ("00" + (this.sequence_funcionario + 1)).substring(0, 3);
        String prefixo = "0000" + (this.sequence_funcionario + 1);
        return prefixo.substring(prefixo.length() - 4);
    }
    
    public boolean cpfExiste(int cpf) {
        boolean existe = false;
        
        for (Map.Entry<String, FuncionarioInterface> entry : this.funcionarios.entrySet()) {
            String mat = entry.getKey();
            FuncionarioInterface func = entry.getValue();
            
            if (func.getCpf() == (cpf)) {
                existe = true;
                break;
            }
        }
        
        return existe;
    }
    
    public boolean autenticarUsuario(String login, String senha) throws CredenciaisInvalidasException, FuncionarioNaoEncontradoException, FuncionarioSemPermissaoException {
        if (login.equals("") || senha.equals("")) {
            throw new CredenciaisInvalidasException("Insira um nome de usuario e uma senha");
        }
        
        System.out.println("Tamanho: " + this.funcionarios.size());
        
        if (!this.funcionarios.containsKey(login)) {
            throw new FuncionarioNaoEncontradoException("Usuario Não encontrado");
        }
        
        FuncionarioInterface func = this.funcionarios.get(login);
        if (!func.isAtivo()) {
            throw new FuncionarioSemPermissaoException("Usuário desativado");
        }
        
        String cargo = func.getCargo();
        
        if (!(cargo.equals("diretor") || cargo.equals("secretario"))) {
            throw new FuncionarioSemPermissaoException("Somente Diretores e Secreatarios podem logar no sistema");
        }
        
        if (((FuncionarioInterface) func).getSenha().equals(senha)) {
            this.funcionario_sessao = func;
            return true;
        } else {
            throw new CredenciaisInvalidasException("Senha incorreta");
        }
    }
    
    public ArrayList<FuncionarioInterface> buscar_funcionario() {
        return this.buscar_funcionario(0);
    }

    //0 todos
    //1 Diretores
    //2 Secretarios
    //3 Educadores
    //4 Auxiliares
    public ArrayList<FuncionarioInterface> buscar_funcionario(int tipo) {
        ArrayList<FuncionarioInterface> result_set = new ArrayList<FuncionarioInterface>();
        
        Iterator it = ControleFuncionario.getInstance().getFuncionarios().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Funcionario func = (Funcionario) pair.getValue();
            // it.remove(); // avoids a ConcurrentModificationException

            if (tipo == 0) {
                result_set.add(func);
            } else if (tipo == 1 && func.getCargo().equals("diretor")) {
                result_set.add(func);
            } else if (tipo == 2 && func.getCargo().equals("secretario")) {
                result_set.add(func);
            } else if (tipo == 3 && func.getCargo().equals("educador")) {
                result_set.add(func);
            } else if (tipo == 4 && func.getCargo().equals("auxiliar")) {
                result_set.add(func);
            }
            
        }
        
        return result_set;
    }
    
    public FuncionarioInterface buscar_funcionario_indice(int tipo, int indice) {
        ArrayList<FuncionarioInterface> result_set = new ArrayList<FuncionarioInterface>();
        Funcionario funcionario = null;
        int i = 0;
        Iterator it = ControleFuncionario.getInstance().getFuncionarios().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Funcionario func = (Funcionario) pair.getValue();
            // it.remove(); // avoids a ConcurrentModificationException

            if (tipo == 0) {
                result_set.add(func);
            } else if (tipo == 1 && func.getCargo().equals("diretor")) {
                result_set.add(func);
            } else if (tipo == 2 && func.getCargo().equals("secretario")) {
                result_set.add(func);
            } else if (tipo == 3 && func.getCargo().equals("educador")) {
                result_set.add(func);
            } else if (tipo == 4 && func.getCargo().equals("auxiliar")) {
                result_set.add(func);
            }
            
            if (result_set.size() == (indice + 1)) {
                funcionario = func;
                break;
            }
            i++;
        }
        
        return funcionario;
    }
    
    public int buscar_indice_funcionario(int tipo, int cpf) {
        ArrayList<Funcionario> result_set = new ArrayList<Funcionario>();
        int i = 0;
        int indice = -1;
        Iterator it = ControleFuncionario.getInstance().getFuncionarios().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Funcionario func = (Funcionario) pair.getValue();
            // it.remove(); // avoids a ConcurrentModificationException

            if (tipo == 0) {
                result_set.add(func);
            } else if (tipo == 1 && func.getCargo().equals("diretor")) {
                result_set.add(func);
            } else if (tipo == 2 && func.getCargo().equals("secretario")) {
                result_set.add(func);
            } else if (tipo == 3 && func.getCargo().equals("educador")) {
                result_set.add(func);
            } else if (tipo == 4 && func.getCargo().equals("auxiliar")) {
                result_set.add(func);
            }
            
            if (i == result_set.size()) {
                indice = i;
                break;
            }
            i++;
        }
        return indice;
    }
    
    public void escreveFuncionariosComSenhaTeste() {
        for (Map.Entry<String, FuncionarioInterface> entry : this.funcionarios.entrySet()) {
            String mat = entry.getKey();
            FuncionarioInterface func = entry.getValue();
            
            System.out.println(mat + " - " + func.getSenha());
        }
    }
    
    public FuncionarioInterface buscar_funcionario_cpf(int cpf) {
        FuncionarioInterface funcionario = null;
        
        for (Map.Entry<String, FuncionarioInterface> entry : this.funcionarios.entrySet()) {
            String mat = entry.getKey();
            FuncionarioInterface func = entry.getValue();
            
            if (func.getCpf() == cpf) {
                funcionario = func;
                break;
            }
        }
        
        return funcionario;
    }
    
    public String[][] getFunc_matriz() {
        ArrayList<FuncionarioInterface> func_set = ControleFuncionario.getInstance().buscar_funcionario(0);
        int list_size = func_set.size();
        String[][] list_set = new String[list_size][4];
        
        for (int i = 0; i < list_size; i++) {
            list_set[i][0] = func_set.get(i).getNome();
            list_set[i][1] = func_set.get(i).getCargo();
            list_set[i][2] = func_set.get(i).getCpf() + "";
            list_set[i][3] = func_set.get(i).getMatricula();
        }
        return list_set;
    }
    
    public String[] getFuncrStringArray(int tipo) {
        ArrayList<FuncionarioInterface> func_set = this.buscar_funcionario(tipo);
        int list_size = func_set.size();
        String[] list_set = new String[list_size];
        
        for (int i = 0; i < list_size; i++) {
            list_set[i] = func_set.get(i).getNome();
        }
        return list_set;
    }
    
}
