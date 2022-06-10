package tabelas;

import java.util.Scanner;
import java.util.ArrayList;

/*
---------------Nomes:-------------------
|									   |
| - Camilo Augusto Borges Caballero    |
| - Cesar Cunha Ziobro			       |
| - Enzo Bloss Stival				   |
| - lucas philippe nunes de lima       |
| - Rafael Schmitz Herdt			   |
|								       |
----------------------------------------	
*/

public class TabelaVerdade {
	
	// ===========================================================================
	// Criando a parte para printar os bits como V e F
	// ===========================================================================
	
	public char printBit(boolean p) {
		
		return p ? 'V' : 'F';

	} // fim do printBit
	
	
	// ===========================================================================
	// formula para printar a formula passada pelo scanner entre parenteses
	// ===========================================================================
	
	public void printFormula(String FormulaCorreta) {
		
		System.out.print(" (" + FormulaCorreta + ") " );
		
	} // fim do printFormula
	
	
	// ===========================================================================
	// Função para Tabelas com uma Variavel
	// ===========================================================================
	
	public void tabelaUmaVariavel(String formulaCorreta) {
		
		int i;
		boolean p,resp;
		
		System.out.println("\n#-=-#-=-=-=-#");
		
		if (formulaCorreta.length() == 1) {
			System.out.print  ("| P | ");
		}
		else {
			System.out.print  ("| P |");
		}
		printFormula(formulaCorreta);
		
		
		System.out.print(" | \n");
	
		
		System.out.println("#-=-#-=-=-=-#");
		
		for (i = 1; i >= 0; i--) {
			
			switch(i) {
				case 1:
					p = true;
					break;
				default:
					p = false;
					break;
			} // fim do Switch Case
			
			// condição ternario -> x = (expressão booleana) ? codigo pra verdade : codigo para falso;
			resp = (formulaCorreta.charAt(0) == '~')? !p : p;
			System.out.print("| " + printBit(p) + " | ");
			System.out.println("  " + printBit(resp) + "   | ");
			
			
			
		}// fim do for
		System.out.println("#-=-#-=-=--=#");
		
	}// fim da função TabelaUmaVariavel
		

	// ===========================================================================
	// Função para Tabelas com duas Variaveis
	// ===========================================================================
	
	public void tabelaDuasVariaveis(String formula) {
		
		int i;
		boolean p,q, resp = true;
		
		System.out.println("#-=-#-=-#");
		System.out.print  ("| p | q |"); 
		
		printFormula(formula);
		
		System.out.println();
		System.out.println("#-=-#-=-#");
		
		for (i = 3; i >= 0; i--) {
			
			switch(i) {
			
				case 3:
					p = true ; q = true;
					break;
				case 2:
					p = true ; q = false;
					break;
				case 1:
					p = false; q = true;
					break;
				default:
					p = false; q = false;
			
			}// fim do Switch/case
			
			if (formula.length() == 3) {// formulas com 3 caracteres
				resp = formula.charAt(1) == '^'? p && q : p || q;
			}
			else if (formula.length() == 4) {// formulas com 4 caracteres
				
				 if (formula.charAt(0) == '~') {// negando p
					 resp = formula.charAt(2) == '^'? (!p && q) : (!p || q); 
				 }
				 else if (formula.charAt(2) == '~') { // negando q
					 resp = formula.charAt(1) == '^'? (p && !q) : (p || !q); 
				 }
			}
		    else if (formula.length() == 5) {// formula com 5 caracteres
			    resp = formula.charAt(2) == '^'? (!p && !q) : (!p || !q);
		    }
			
			System.out.print("| " + printBit(p) + " | " + printBit(q));
			System.out.println(" |   " + printBit(resp));
			
			
			
		}// fim do for
		
		System.out.println("#-=-#-=-#");
		
		
		
	}// fim da função tabelaDuasVariaveis
	
	
	// ===========================================================================
	// Função para Tabelas com tres Variaveis
	// ===========================================================================
	
	public void tabelaTresVariaveis(String formula) {
		
		int i;
		boolean p,q,r, resp = true;
		
		System.out.println("#-=-#-=-#-=-#");
		System.out.print  ("| p | q | r | ");
		printFormula(formula);
		System.out.println();
		System.out.println("#-=-#-=-#-=-#");
		
		
		for (i = 7; i >= 0; i--) {
			
			switch(i) {
			
				case 7:
					p = true; q = true; r = true;
					break;
				case 6:
					p = true; q = true; r = false;
					break;
				case 5:
					p = true; q = false; r = true;
					break;
				case 4:
					p = true; q = false; r = false;
					break;
				case 3:
					p = false; q = true; r = true;
					break;
				case 2:
					p = false; q = true; r = false;
					break;
				case 1:
					p = false; q = false; r = true;
					break;
				default:
					p = false; q = false; r = false;
					
			}
			
			// 5 CARACTERES
			if (formula.length() == 5) { // formulas p ^ q ^ r | p ^ q v r | p v q ^ r | p v q v r
				if (formula.charAt(1) == '^') {
					resp = formula.charAt(3) == '^'? (p&&q&&r) : (p&&q||r);
				}
				else {
					resp = formula.charAt(3) == '^'? (p||q&&r) : (p||q||r);
				}
			}
			// 6 CARACTERES
			else if ((formula.length() == 6)   &&  // formula ~p ^ q ^ r | ~p ^ q v r | ~p v q ^ r | ~p v q v r
					 (formula.charAt(0) == '~') && 
					 (formula.charAt(1) == 'p') &&
					 (formula.charAt(3) == 'q') &&
					 (formula.charAt(5) == 'r')) {
					if (formula.charAt(2) == '^') {
						resp = formula.charAt(4) == '^'? (!p&&q&&r) : (!p&&q||r);
					}
					else if (formula.charAt(2) == 'v') {
						resp = formula.charAt(4) == '^'? (!p||q&&r) : (!p||q||r);
					}
			}else if ((formula.length() == 6)   &&  // formula p ^ ~q ^ r | p ^ ~q v r | p v ~q ^ r | p v ~q v r
					 (formula.charAt(0) == 'p') && 
					 (formula.charAt(2) == '~') &&
					 (formula.charAt(3) == 'q') &&
					 (formula.charAt(5) == 'r')) {
					if (formula.charAt(1) == '^') {
						resp = formula.charAt(4) == '^'? (p&&!q&&r) : (p&&!q||r);
					}
					else if (formula.charAt(1) == 'v') {
						resp = formula.charAt(4) == '^'? (p||!q&&r) : (p||!q||r);
					}
					
		  }else if ((formula.length() == 6)   &&  // formula p ^ q ^ !r | p ^ q v !r | p v q ^ !r | p v q v !r
					 (formula.charAt(0) == 'p') && 
					 (formula.charAt(2) == 'q') &&
					 (formula.charAt(4) == '~') &&
					 (formula.charAt(5) == 'r')) {
					if (formula.charAt(1) == '^') {
						resp = formula.charAt(3) == '^'? (p&&q&&!r) : (p&&q||!r);
					}
					else if (formula.charAt(1) == 'v') {
						resp = formula.charAt(3) == '^'? (p||q&&!r) : (p||q||!r);
					}
		  }
			
		 // 7 CARACTERES
			
		  else if ((formula.length() == 7)   &&  // formula ~p ^ ~q ^ r | ~p ^ ~q v r | ~p v ~q ^ r | ~p v ~q v r
					 (formula.charAt(0) == '~') && 
					 (formula.charAt(1) == 'p') &&
					 (formula.charAt(3) == '~') &&
					 (formula.charAt(4) == 'q') &&
					 (formula.charAt(6) == 'r')) {
					if (formula.charAt(2) == '^') {
						resp = formula.charAt(5) == '^'? (!p&&!q&&r) : (!p&&!q||r);
					}
					else if (formula.charAt(2) == 'v') {
						resp = formula.charAt(5) == '^'? (!p||!q&&r) : (!p||!q||r);
					}
		  }
		  else if ((formula.length() == 7)   &&  // formula ~p ^ q ^ ~r | ~p ^ q v ~r | ~p v q ^ ~r | ~p v q v ~r
					 (formula.charAt(0) == '~') && 
					 (formula.charAt(1) == 'p') &&
					 (formula.charAt(3) == 'q') &&
					 (formula.charAt(5) == '~') &&
					 (formula.charAt(6) == 'r')) {
					if (formula.charAt(2) == '^') {
						resp = formula.charAt(4) == '^'? (!p&&q&&!r) : (!p&&q||!r);
					}
					else if (formula.charAt(2) == 'v') {
						resp = formula.charAt(4) == '^'? (!p||q&&!r) : (!p||q||!r);
					}
		  }
		  else if ((formula.length() == 7)   &&  // formula p ^ ~q ^ ~r | p ^ ~q v ~r | p v ~q ^ ~r | p v ~q v ~r
					 (formula.charAt(0) == 'p') && 
					 (formula.charAt(2) == '~') &&
					 (formula.charAt(3) == 'q') &&
					 (formula.charAt(5) == '~') &&
					 (formula.charAt(6) == 'r')) {
					if (formula.charAt(1) == '^') {
						resp = formula.charAt(4) == '^'? (p&&!q&&!r) : (p&&!q||!r);
					}
					else if (formula.charAt(1) == 'v') {
						resp = formula.charAt(4) == '^'? (p||!q&&!r) : (p||!q||!r);
					}
		  }
			
		// 8 CARACTERES		
		  else if ((formula.length() == 8)   &&  // formula ~p ^ ~q ^ ~r | ~p ^ ~q v ~r | ~p v ~q ^ ~r | ~p v ~q v ~r
					 (formula.charAt(0) == '~') && 
					 (formula.charAt(1) == 'p') &&
					 (formula.charAt(3) == '~') &&
					 (formula.charAt(4) == 'q') &&
					 (formula.charAt(6) == '~') &&
					 (formula.charAt(7) == 'r')) {
					if (formula.charAt(2) == '^') {
						resp = formula.charAt(5) == '^'? (!p&&!q&&!r) : (!p&&!q||!r);
					}
					else if (formula.charAt(2) == 'v') {
						resp = formula.charAt(5) == '^'? (!p||!q&&!r) : (!p||!q||!r);
					}
		  }
			
			System.out.print("| " + printBit(p) + " | " + printBit(q) + " | " + printBit(r));
			System.out.println(" |   " + printBit(resp));
			
			
		} // fim do for
		
		System.out.println("#-=-#-=-#-=-#");
		
		
		
	}// Fim da Função tabelaTresVariaveis
	
	
	// ===========================================================================
	// função para conferir se formulas com uma variavel estão corretas
	// ===========================================================================
	
	private boolean achaTabelaUmaVariavel(String formula) {
		
		if ((formula.charAt(0) == '~' && formula.charAt(1) == 'p' ) ||
			(formula.charAt(0) == 'p' && formula.length()  ==  1))	{
			
			tabelaUmaVariavel(formula);
			return true;
		} // if
		else {
			return false;
		} // else
		
		
		
	} // fim da funçao achaTabelaUmaVariavel
	
	
	// ===========================================================================
	// função para conferir se formulas com duas variaveis estão corretas
	// ===========================================================================
	
	private boolean achaTabelaDuasVariaveis(String formula) {
		
		boolean formOk = false;
		
		if ((formula.length()  == 3)   && // caso onde tem 3 caracteres
			(formula.charAt(0) == 'p') &&
			(formula.charAt(2) == 'q')) {
				if ((formula.charAt(1) == '^') ||			// p ^ q
					(formula.charAt(1) == 'v')	)	// p v q
					formOk = true;
					tabelaDuasVariaveis(formula);
					
		}else if ((formula.length() == 4)    && // caso onde tem 4 caracteres e começa com negação
				 (formula.charAt(0) == '~')  &&
				 (formula.charAt(1) == 'p')  &&
				 (formula.charAt(3) == 'q')) {
					if ((formula.charAt(2) == '^') ||			// ~p ^ q
						(formula.charAt(2) == 'v')	) 		// ~p v q
						formOk = true;	
						tabelaDuasVariaveis(formula);
					
		}else if ((formula.length() == 4)    && // caso onde tem 4 caracteres e a negação ta no q
				 (formula.charAt(0) == 'p')  &&
				 (formula.charAt(2) == '~')  &&
				 (formula.charAt(3) == 'q')) {
					if ((formula.charAt(1) == '^') ||			// p ^ ~q
						(formula.charAt(1) == 'v')	) 		// p v ~q
						formOk = true;
						tabelaDuasVariaveis(formula);
						
		}else if ((formula.length()  == 5)   && // ~p ^ ~q
				 (formula.charAt(0) == '~')  &&
				 (formula.charAt(1) == 'p')  &&
				 (formula.charAt(3) == '~')  &&
				 (formula.charAt(4) == 'q')) {
					if ((formula.charAt(2) == '^') ||
						(formula.charAt(2) == 'v'))
						formOk = true;
						tabelaDuasVariaveis(formula);
							
		}else
				formOk = false;
		
	
		
		return formOk;


		
	}// fim da função achaTabelaDuasVariaveis
	
	
	// ===========================================================================
	// função para conferir se formulas com tres variaveis estão corretas
	// ===========================================================================
	
	// ===========================================================================
	// função para conferir se formulas com tres variaveis estão corretas
	// ===========================================================================
		
	private boolean achaTabelaTresVariaveis(String formula) {
		boolean formOk = false;
		ArrayList<String> formulas_possiveis = new ArrayList<String>();
		switch(formula.length()){
			case 5:
				String[] formulas_5 = {"pvqvr", "p^qvqr", "p^q^r", "pvq^r"};
				for(int i = 0; i<formulas_5.length; i++){
					formulas_possiveis.add(formulas_5[i]);
				}
				break;
			case 6:
				String[] formulas_6 = {"~pvqvr","~pvq^r", "~p^qvr", "~p^q^r", "pv~qvr", "p^~qvr", "pv~q^r", "p^~q^r", "pvqv~r", "p^qv~r", "pvq^~r", "p^q^~r"};
				for(int i = 0; i<formulas_6.length; i++){
					formulas_possiveis.add(formulas_6[i]);
				}
				break;
			case 7:
		
				String[] formulas_7 = {"~pv~qvr","~pv~q^r", "~p^~q^r", "~p^~qvr", "~pvqv~r", "~p^qv~r", "~pvq^~r", "~p^q^~r","pv~qv~r", "p^~qv~r", "pv~q^~r", "p^~q^~r"};
				for(int i = 0; i<formulas_7.length; i++){
					formulas_possiveis.add(formulas_7[i]);
				}
				break;
				
			case 8:
				String[] formulas_8 = {"~p^~q^~r", "~p^~qv~r", "~pv~q^~r", "~pv~qv~r"};
				for(int i = 0; i<formulas_8.length; i++){
					formulas_possiveis.add(formulas_8[i]);
				}
				break;
			
		}
		
		if (formulas_possiveis.contains(formula)){
			formOk = true;
			tabelaTresVariaveis(formula);
		} // nao pode invocar contains(string) em um contains(string[])
		
		
		return formOk;
		
	}// fim da função achaTabelaTresVariaveis
	
	
	
	// ===========================================================================
	// Main menu --- parte principal
	// ===========================================================================
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		String formula;
		boolean tabOk = false;
		
		TabelaVerdade tb = new TabelaVerdade();
		
		System.out.println("#-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-#");
		System.out.println("                                     Seja bem-vindo ao gerador de tabelas verdades \n                                           ");
		System.out.println("Temos algumas regras que precisam ser seguidas para o funcionamento                                                             ");
		System.out.println("1. So funcionamos com as operações de v (ou), ^ (e) e ~ (nao), pedimos que utilize esse formato para a boa execução do programa ");
		System.out.println("2. Funcionamos com no maximo 3 variaveis, sendo elas -> p q r                                                                    ");
		System.out.println("#-=-=-=-=-=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-#");
		
		System.out.print("Entre com a fórmula: ");
		formula = entrada.nextLine();
		formula = formula.toLowerCase();
		formula = formula.replaceAll(" ", "");
		
		
		switch (formula.length()) {
		
		case 1:
		case 2:
			tabOk = tb.achaTabelaUmaVariavel(formula);
			break;
		case 3:
		case 4:
			tabOk = tb.achaTabelaDuasVariaveis(formula);
			break;
		case 5:
			if (formula.charAt(0) == '~') {
				tabOk = tb.achaTabelaDuasVariaveis(formula);
				break;
				}
			
			else {
				tabOk = tb.achaTabelaTresVariaveis(formula);
				break;
				}
		case 6:
		case 7:
		case 8:
			tabOk = tb.achaTabelaTresVariaveis(formula);
			break;
			
		default:
			tabOk = false;
		
		}
		if (!tabOk) {
			System.out.println("Não foi possivel gerar a tabela com essa formula, por favor insira uma outra valida!!!");
			entrada.close();
		} 
		
		
	}

}

// EXEMPLOS DO PDF DA PROFESSORA

// p v ~q ^ r
// ~ p v ~ q ^ ~ r

// p v v q ~ r
// ~vp~
// p q v r
