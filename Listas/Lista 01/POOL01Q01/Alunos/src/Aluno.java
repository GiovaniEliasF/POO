public class Aluno {
	private String nome;
	private Integer matricula;
	private Float nota1;
	private Float nota2;
	private Float nota3;
	private Float media;
	private String situacao;

	public Aluno() {

	}

	public void setNome(String novoNome){
		this.nome = novoNome;
	}

	public void setMatricula(Integer novaMatricula){
		this.matricula = novaMatricula;
	}

	public void setNotas(Float n1,Float n2,Float n3){
		this.nota1 = n1;
		this.nota2 = n2;
		this.nota3 = n3;
	}

	public void calcularMedia(){
		media = (nota1+nota2+nota3)/3;
	}

	public boolean buscarAluno (Integer matricula){
		if (matricula==this.matricula){
			return true;
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public Float getNota1() {
		return nota1;
	}

	public Float getNota2() {
		return nota2;
	}

	public Float getNota3() {
		return nota3;
	}

	public Float getMedia() {
		return media;
	}

	public String getSituacao() {
		return situacao;
	}
//parei aqui
	public String alterarNota(int codNota, float valorNota){
		switch (codNota) {
			case 1:
				nota1 = valorNota;
				
				break;
			case 2:
				nota2 = valorNota;
				break;
			case 3:
				nota3 = valorNota;
				break;
			default:
				System.out.println("Nota inserida nao eh valida");
				break;
		}
		return situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nota1 == null) ? 0 : nota1.hashCode());
		result = prime * result + ((nota2 == null) ? 0 : nota2.hashCode());
		result = prime * result + ((nota3 == null) ? 0 : nota3.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nota1 == null) {
			if (other.nota1 != null)
				return false;
		} else if (!nota1.equals(other.nota1))
			return false;
		if (nota2 == null) {
			if (other.nota2 != null)
				return false;
		} else if (!nota2.equals(other.nota2))
			return false;
		if (nota3 == null) {
			if (other.nota3 != null)
				return false;
		} else if (!nota3.equals(other.nota3))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (situacao == null) {
			if (other.situacao != null)
				return false;
		} else if (!situacao.equals(other.situacao))
			return false;
		return true;
	}
	
}