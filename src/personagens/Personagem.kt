package personagens

import equipamentos.*
import java.util.*

abstract class Personagem(val nome: String) {

    var nivelEnergia    = 100f
    var poderDefesa     = 0
    var armas: MutableList<Arma> = mutableListOf()
    var armadura: Armadura? = null

    abstract val SOCO: Float
    abstract val CHUTE: Float
    private val random = Random()
    private val DESGASTE_DEFESA = 10

    abstract fun atacar(p: Personagem)

    fun defender(atacante: Personagem, poderAtaque: Float){
        if (atacante.isAlive()) {
            if (!esquivou()) {
                if (poderAtaque > this.poderDefesa) {
                    var dano = poderAtaque - this.poderDefesa
                    if (dano >= nivelEnergia) dano = nivelEnergia
                    nivelEnergia -= dano
                    if (armadura != null) armadura!!.desgastar()
                    if (DESGASTE_DEFESA <= poderDefesa) poderDefesa -= DESGASTE_DEFESA
                    print(" ${nome.toUpperCase()} -$dano hp")
                } else {
                    if (DESGASTE_DEFESA <= poderDefesa) poderDefesa -= DESGASTE_DEFESA
                    print(" $nome se defendeu do golpe de ${atacante.nome}!")
                }
            } else {
                print("\n${this.nome} esquivou do ataque de ${atacante.nome}! Nenhum dano!")
            }
        }
    }

    fun isAlive(): Boolean {
        return nivelEnergia>0
    }

    fun socar(): Float {
        print("$nome deu um soco!")
        return SOCO
    }

    fun chutar(): Float {
        print("$nome deu um chute!")
        return CHUTE
    }

    private fun esquivou(): Boolean {
        val random = Random()
        return ((1 + random.nextInt()%4) == 1)
    }

    fun apresentar() {
        var classe = ""
        when {
            this is Guerreiro -> classe = "Guerreiro"
            this is Feiticeiro -> classe = "Feiticeiro"
            this is Besta -> classe = "Besta"
        }
        println("\nNOME: $nome\n" +
                "CLASSE: $classe\n" +
                "ENERGIA: $nivelEnergia\n" +
                "ARMAS: ${this.contarArmas()}\n" +
                "DEFESA: $poderDefesa")
    }

    fun status(){
        println("\n${nome.toUpperCase()}: ($nivelEnergia hp)\n")
    }

    fun getChance(bound: Int): Int {
        return 1 + random.nextInt()%bound
    }

    fun hasArma(): Boolean {
        return armas.isNotEmpty()
    }

    fun gerarArma(dono: Personagem): Arma? {
        if (dono is Guerreiro) {
            return when(getChance(2)) {
                1 -> Espada(dono)
                else -> Machado(dono)
            }
        }
        else if (dono is Feiticeiro) {
            return when (getChance(2)) {
                1 -> Livro(dono)
                else -> Cajado(dono)
            }
        }
        return null
    }


    fun atacarIfHasArma(atacante: Personagem, atacado: Personagem) {
        val sortArma: Arma
        if (hasArma()) {
            sortArma = armas[random.nextInt(armas.count())]
            sortArma.usar(atacante, atacado)
        }
        else atacado.defender(atacante, chutar())
    }

    private fun contarArmas(): String {
        if (hasArma()){
            if (this is Guerreiro){
                var espadas=0
                var machados=0
                for (i in armas){
                    if (i is Espada) espadas++
                    else if (i is Machado) machados++
                }
                return "$espadas espada(s) e $machados machado(s)"
            } else if (this is Feiticeiro){
                var cajados=0
                var livros=0
                for (i in armas){
                    if (i is Cajado) cajados++
                    else if (i is Livro) livros++
                }
                return "$cajados cajado(s) e $livros livro(s)"
            }
        }
        return "não possui armas"
    }
}