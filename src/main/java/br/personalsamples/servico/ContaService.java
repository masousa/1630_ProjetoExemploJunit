package br.personalsamples.servico;

import br.personalsamples.dominio.Conta;

public class ContaService {

	public boolean isValidAccount(Conta conta){
		return conta.isAtiva();
	}
}
