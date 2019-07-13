package application.exception;


import application.classes.Atividade;
import application.classes.DadosFuncionario;
import application.classes.Funcionario;
import application.classes.Linguagem;
import application.classes.Plano;
import application.classes.Projeto;
import application.dao.Dao;
import application.dao.DaoGeneric;

public class Tratamento {
	
	public String inserir(Funcionario func) throws RunException{
		
		if(func.getLogin_funcionario().length() < 6) {
			throw new RunException("Erro: Login muito curto, insira novamente!");
		}
		
		else if(func.getSenha_funcionario().length() < 6) {
			throw new RunException("Erro: Senha muito curta, favor insira mais caracteres!");
		}
		else {
			Dao<Funcionario> Daofuncionario = new DaoGeneric<Funcionario>();
			Daofuncionario.inserir(func);
			return null;	
		}	
		
	}
	public String inserirDado(DadosFuncionario dados) throws RunException {
			
		if(dados.getNome_funcionario().length() < 2) {
			throw new RunException("Erro: Nome muito curto, insira novamente!");
		}
		
		else if(dados.getSobrenome_funcionario().length() < 6) {
			throw new RunException("Erro: Senha muito curta, favor insira mais caracteres!");
		}
		else {
			Dao<DadosFuncionario> daoDados = new DaoGeneric<DadosFuncionario>();
			daoDados.inserir(dados);
			return null;	
		}	
			
	}
	public String inserirProjeto(Projeto projeto) throws RunException {
		
		if(projeto.getNome_projeto().length() < 2) {
			throw new RunException("Erro: Nome muito curto, insira novamente!");
		}
		
		else if(projeto.getCusto() < 0) {
			throw new RunException("Erro: Preço inválido, favor insira o preço corretamente!");
		}
		else {
			Dao<Projeto> daoProjeto = new DaoGeneric<Projeto>();
			daoProjeto.inserir(projeto);
			return null;	
		}	
			
	}
	public String inserirPlano(Plano plano) throws RunException {
		
		if(plano.getNome_plano().length() < 1) {
			throw new RunException("Erro: Nome muito curto, insira novamente!");
		}

		else {
			Dao<Plano> daoPlano = new DaoGeneric<Plano>();
			daoPlano.inserir(plano);
			return null;	
		}	
			
	}
	public String inserirLinguagem(Linguagem linguagem) throws RunException {
		
		if(linguagem.getNome_linguagem().length() < 2) {
			throw new RunException("Erro: Nome muito curto, insira novamente!");
		}
		else if(linguagem.getCarga_horaria() <= 0) {
			throw new RunException("Erro: Carga horária não aceita, favor contatar o Administrador do sistema!");
		}
		else {
			Dao<Linguagem> daoLinguagem = new DaoGeneric<Linguagem>();
			daoLinguagem.inserir(linguagem);
			return null;	
		}		
	}
	public String inserirAtividade(Atividade atividade) throws RunException {
		
		if(atividade.getTotal_dias_fazendo() < 0) {
			throw new RunException("Erro: Dias inválidos, insira novamente!");
		}
		else {
			Dao<Atividade> daoAtividade = new DaoGeneric<Atividade>();
			daoAtividade.inserir(atividade);
			return null;	
		}		
	}
	
}
