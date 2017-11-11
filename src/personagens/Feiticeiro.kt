package personagens

import equipamentos.Armadura

class Feiticeiro(nome: String) : Personagem(nome) {


    override val SOCO       = 20f
    override val CHUTE      = 25f

    private val ATAQUE_RAIOS = 30f

    init {
        if (getChance(2)==1){
            armas.add(this.gerarArma(this)!!)
            armadura = Armadura(this)
        }
    }

    override fun atacar(p: Personagem) {
        val chance = getChance(4)

        when (chance) {
            1 -> p.defender(this, raios())
            2 -> p.defender(this, chutar())
            3 -> atacarIfHasArma(this, p)
            4 -> sugarVida(p)
            else -> p.defender(this, socar())
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