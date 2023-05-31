package br.com.padroesspring.service.impl;

import br.com.padroesspring.model.Cliente;
import br.com.padroesspring.model.ClienteRepository;
import br.com.padroesspring.model.Endereco;
import br.com.padroesspring.model.EnderecoRepository;
import br.com.padroesspring.service.ClienteService;
import br.com.padroesspring.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 *  Service Ela será tratada como um <b>Singleton</b>
 *
 * @author LeonardoRSantos
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    //Singleton: Injetar os componentes do Spring com @Autowired.
    //Strategy: Implementar os métodos definidos na interface.
    //Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    //caso o metodo não funcione acima descomente esse=
    /*
     @Override
     public Cliente buscarPorId(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
     }

     */


    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }



    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()){
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            return enderecoRepository.save(novoEndereco);
            //caso não der certo faça assim
            /*
            enderecoRepository.save(novoEndereco);
            return endereco;
             */
        } );
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
