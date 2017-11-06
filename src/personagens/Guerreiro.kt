package personagens

class Guerreiro(nome: String) : Personagem(nome) {

    override val SOCO       = 20f
    override val CHUTE      = 25f

    private val PODER_CURA  = 25f

    init {
        if (quarterOfChance()){
            armas.add(this.gerarArma(this)!!)
        }
    }

    override fun atacar(p: Personagem) {
        var chance: Int

        //não permitirá que o primeiro movimento seja o de cura
        do {
            chance = getChance(4)
        } while (chance == 1 && nivelEnergia>=75)


        when (chance) {
            1 ->  curar()
            2 -> p.defender(this, chutar())
            3 -> atacarIfHasArma(this, p)
            else -> p.defender(this, socar())
        }
    }

    private fun curar() {
        print("$nome usou cura! +$PODER_CURA hp!")
        if (nivelEnergia >= 75) nivelEnergia = 100f
        else nivelEnergia += PODER_CURA
    }
}