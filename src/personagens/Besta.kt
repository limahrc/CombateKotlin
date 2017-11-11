package personagens

import equipamentos.Armadura

class Besta(nome: String) : Personagem(nome) {
    override val SOCO   = 30f
    override val CHUTE  = 35f

    private val ATAQUE_GARRAS = 39f

    init {
        if (getChance(4)==1){
            armadura = Armadura(this)
        }
    }

    override fun atacar(p: Personagem) {
        var chance: Int

        //não permitirá que a besta use 'esmagar' antes da hora
        do {
            chance = getChance(4)
        } while (chance == 1 && p.nivelEnergia>=50)

        when (chance){
            1 -> esmagar(p)
            2 -> p.defender(this, chutar())
            3 -> p.defender(this, garras())
            else -> p.defender(this, socar())
        }
    }


    private fun garras(): Float {
        print("$nome usou ataque de garras!")
        return ATAQUE_GARRAS
    }

    private fun esmagar(p: Personagem) {
        if (p.nivelEnergia>0 && p.nivelEnergia<=50){
            p.nivelEnergia = 0f
            print("FATALIDADE: $nome esmagou ${p.nome} até a morte.")
        }
    }

}