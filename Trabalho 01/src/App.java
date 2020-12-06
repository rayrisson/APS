import java.util.ArrayList;
import java.util.Scanner;

class Produto{
    private int codigo, qtdEstoque;
    private String descricao;
    private double valor, custo, lucro;

    Produto(int codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }
    Produto(int codigo, String descricao, double valor, double custo, double lucro){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.custo = custo;
        this.lucro = lucro;
    }
    Produto(int codigo, String descricao, double valor, double custo, double lucro, int qtdEstoque){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.custo = custo;
        this.lucro = lucro;
        this.qtdEstoque = qtdEstoque;
    }

    public void compra(int qtd){
        qtdEstoque += qtd;
    }
    public void venda(int qtd){
        qtdEstoque -= qtd;
    }
    public double calculaPrecoVenda(){
        return valor + custo + lucro * (valor + custo);
    }
    public int getCod(){
        return codigo;
    }
    public double getValor(){
        return valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }
    public double getCusto(){
        return custo;
    }
    public void setCusto(double custo){
        this.custo = custo;
    }
    public double getLucro(){
        return lucro;
    }
    public void setLucro(double lucro){
        this.lucro = lucro;
    }
    public int getQtdEstoque(){
        return qtdEstoque;
    }

    @Override
    public String toString(){
        return "Código: " + codigo + " Descrição: "+ descricao + " Preço: " + calculaPrecoVenda();
    }
}

class RevendaComArray{
    private ArrayList<Produto> produtos;

    RevendaComArray(int qtdProdutos){
        produtos = new ArrayList<>(qtdProdutos);
    }

    void inserirProdutos(Produto prod){
        produtos.add(prod);
    }
    void comprar(int codigoProd, int qtd){
        for(Produto prod : produtos){
            if(prod.getCod() == codigoProd){
                prod.compra(qtd);
                return;
            }
        }
        System.out.println("Produto não existente");
    }
    void vender(int codigoProd, int qtd){
        for(Produto prod : produtos){
            if(prod.getCod() == codigoProd){
                if(prod.getQtdEstoque() >= qtd){
                    prod.venda(qtd);
                }else{
                    System.out.println("Quantidade indisponivel");
                }
                return;
            }
        }
        System.out.println("Produto não existente");
    }
    void consultaPrecoVenda(int codigoProd){
        for(Produto prod : produtos){
            if(prod.getCod() == codigoProd){
                System.out.println("Preço: " + prod.calculaPrecoVenda());
                return;
            }
        }
        System.out.println("Produto não existente");
    }
    void listaPrecos(){
        for(Produto prod : produtos){
            System.out.println(prod);
        }
    }
    void atualizarVCL(int codigoProd, double valor, double custo, double lucro){
        for(Produto prod : produtos){
            if(prod.getCod() == codigoProd){
                prod.setValor(valor);
                prod.setCusto(custo);
                prod.setLucro(lucro);
                return;
            }
        }
        System.out.println("Produto não existente");
    }
    void consultarVCL(int codigoProd){
        for(Produto prod : produtos){
            if(prod.getCod() == codigoProd){
                System.out.println("Valor: " + prod.getValor() + " Custo: " + prod.getCusto() + " Lucro: " + prod.getLucro());
                return;
            }
        }
        System.out.println("Produto não existente");
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        RevendaComArray revenda = new RevendaComArray(0);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            String cmd[] = line.split(" ");
            try{
                if(cmd[0].equals("criar")){
                    Produto prod = null;
                    if(cmd.length == 3){
                        prod = new Produto(Integer.parseInt(cmd[1]), cmd[2]);
                    }else if(cmd.length == 6){
                        prod = new Produto(Integer.parseInt(cmd[1]), cmd[2], Double.parseDouble(cmd[3]), Double.parseDouble(cmd[4]), Double.parseDouble(cmd[5]));
                    }else if(cmd.length == 7){
                        prod = new Produto(Integer.parseInt(cmd[1]), cmd[2], Double.parseDouble(cmd[3]), Double.parseDouble(cmd[4]), Double.parseDouble(cmd[5]), Integer.parseInt(cmd[6]));
                    }else{
                        System.out.println("Parametros inválidos");
                    }
                    if(prod != null){
                        revenda.inserirProdutos(prod);
                    }
                }else if(cmd[0].equals("comprar")){
                    revenda.comprar(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                }else if(cmd[0].equals("vender")){
                    revenda.vender(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]));
                }else if(cmd[0].equals("consultaPreco")){
                    revenda.consultaPrecoVenda(Integer.parseInt(cmd[1]));
                }else if(cmd[0].equals("listaPrecos")){
                    revenda.listaPrecos();
                }else if(cmd[0].equals("atualizarVCL")){
                    revenda.atualizarVCL(Integer.parseInt(cmd[1]), Double.parseDouble(cmd[2]), Double.parseDouble(cmd[3]), Double.parseDouble(cmd[4]));;
                }else if(cmd[0].equals("consultaVCL")){
                    revenda.consultarVCL(Integer.parseInt(cmd[1]));
                }else if(cmd[0].equals("sair")){
                    break;
                }else{
                    System.out.println("Comando inválido");
                }
            }catch(NumberFormatException exception){
                System.out.println("Parametros inválidos");
            }catch(ArrayIndexOutOfBoundsException exception){
                System.out.println("Parametros insuficientes");
            }
        }
        scanner.close();
    }
}
