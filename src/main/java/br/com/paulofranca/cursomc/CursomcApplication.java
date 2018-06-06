package br.com.paulofranca.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paulofranca.cursomc.model.Categoria;
import br.com.paulofranca.cursomc.model.Cidade;
import br.com.paulofranca.cursomc.model.Cliente;
import br.com.paulofranca.cursomc.model.Endereco;
import br.com.paulofranca.cursomc.model.Estado;
import br.com.paulofranca.cursomc.model.Produto;
import br.com.paulofranca.cursomc.model.enums.TipoCliente;
import br.com.paulofranca.cursomc.repositories.CategoriaRepository;
import br.com.paulofranca.cursomc.repositories.CidadeRepository;
import br.com.paulofranca.cursomc.repositories.ClienteRepository;
import br.com.paulofranca.cursomc.repositories.EnderecoRepository;
import br.com.paulofranca.cursomc.repositories.EstadoRepository;
import br.com.paulofranca.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		// adicionando produto(s) a sua respectiva categoria(s)
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		// adicionando categoria(s) a seus respectivos produto(s)
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		System.out.println("------- Adicionando Categorias e Produtos --------------");
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		System.out.println("----------- FIM --------------");

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		System.out.println("------- Adicionando Estados e Cidades --------------");
		this.estadoRepository.saveAll(Arrays.asList(est1, est2));
		this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		System.out.println("----------- FIM --------------");
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		System.out.println("------- Adicionando Clientes e Endereços --------------");
		this.clienteRepository.saveAll(Arrays.asList(cli1));
		this.enderecoRepository.saveAll(Arrays.asList(e1, e2));
		System.out.println("----------- FIM --------------");
	}
}
