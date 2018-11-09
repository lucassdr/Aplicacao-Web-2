package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import componentes.CaminhoURL;
import modelo.dao.EmpresaDAO;
import modelo.dao.FuncionarioDAO;
import modelo.dominio.Empresa;
import modelo.dominio.Funcionario;

@ManagedBean(name = "FuncionarioMB")
@SessionScoped
public class FuncionarioMB {

	// Atributos
	private FuncionarioDAO dao = new FuncionarioDAO();
	private Funcionario funcionario = new Funcionario();

	private List<Funcionario> funcionarios = null;
	private List<Empresa> empresas = null;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		if (this.funcionarios == null)
			this.funcionarios = this.dao.lerTodos();

		return dao.lerTodos();

	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Empresa> getEmpresas() {
		if (this.empresas == null) {
			EmpresaDAO empDAO = new EmpresaDAO();
			this.empresas = empDAO.lerTodos();
		}
		
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	// Métodos
	public String acaoListar() {
		return CaminhoURL.REQUEST_PATH_EMPLOYEE_LIST + CaminhoURL.FACES_REDIRECT;
	}

	public String abrirInclusao() {
		this.funcionario = new Funcionario();

		return CaminhoURL.REQUEST_PATH_EMPLOYEE;
	}

	public String acaoAbrirAlteracao(Integer matricula) {
		this.funcionario = this.dao.lerPorId(matricula);

		return CaminhoURL.REQUEST_PATH_EMPLOYEE;
	}

	public String acaoSalvar() {
		// funcionario.setSenha(senhaTemporaria);
		
		this.dao.salvar(this.funcionario);
		return acaoListar();
	}

	public String acaoExcluir(Integer matricula) {
		this.funcionario = this.dao.lerPorId(matricula);
		if (this.funcionario != null)
			this.dao.excluir(this.funcionario);

		return acaoListar();
	}

	public String menuPrincipal() {
		return CaminhoURL.REQUEST_PATH_HOME + CaminhoURL.FACES_REDIRECT;
	}

}
