package personagens

class Feiticeiro(nome: String) : Personagem(nome) {


    override val SOCO       = 20f
    override val CHUTE      = 25f

    private val ATAQUE_RAIOS = 30f

    init {
        if (quarterOfChance()){
            armas.add(this.gerarArma(this)!!)
        }
    }

    override fun atacar(p: Personagem) {
        var chance: Int

        do {
            chance = getChance(4)
        } while (chance == 4)

        when (chance) {
            1 -> p.defender(p, raios())
            2 -> p.defender(p, chutar())
            3 -> atacarIfHasArma(this, p)
            4 -> sugarVida(p)
            else -> p.defender(p, socar())
        }
    }

    private fun raios(): Float {
        print("$nome usou ataque de raios!")
        return ATAQUE_RAIOS
    }

    private fun sugarVida(p: Personagem){
        var LIFE = 25f
        if (LIFE > p.nivelEnergia) LIFE = p.nivelEnergia
        p.nivelEnergia -= LIFE
        print("$nome absorveu $LIFE hp de ${p.nome}!")
    }
}