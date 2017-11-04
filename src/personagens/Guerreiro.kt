package personagens

import equipamentos.Arma

//classe ainda em desenvolvimento
class Guerreiro(nome: String) : Personagem(nome) {

    override val SOCO       = 20f
    override val CHUTE      = 25f

    private val PODER_CURA  = 25f
    private var armas: MutableList<Arma> = mutableListOf()

    init {
        if (getChance(4) == 1){
            armas.add(Arma(this))
        }
    }

    override fun atacar(p: Personagem) {
        var chance: Int
        var sortArma: Arma = Arma(this)

        //não permitirá que o primeiro movimento seja o de cura
        do {
            chance = getChance(3)
        } while (chance == 1 && nivelEnergia>=75)

        /*do {
            chance = getChance(3)
            if (hasArma()) {
                sortArma = armas.get(random.nextInt(armas.count()))
            }
        } while (chance == 3)*/

        when (chance) {
            1 ->  curar()
            2 -> p.defender(p, chutar())
            3 -> p.defender(p, sortArma.usar())
            else -> p.defender(p, socar())
        }
    }

    private fun curar() {
        print("$nome usou cura! +$PODER_CURA hp!")
        if (nivelEnergia>=75) nivelEnergia = 100f
        else nivelEnergia += PODER_CURA
    }

    private fun hasArma(): Boolean {
        return armas.isNotEmpty()
    }
}