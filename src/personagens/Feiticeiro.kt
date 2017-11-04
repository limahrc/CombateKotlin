package personagens

class Feiticeiro(nome: String) : Personagem(nome) {


    override val SOCO       = 20f
    override val CHUTE      = 25f

    private val ATAQUE_RAIOS = 30f

    override fun atacar(p: Personagem) {
        val chance = 1 + random.nextInt() % 4
        when (chance) {
            1 -> p.defender(p, raios())
            2 -> p.defender(p, chutar())
            else -> p.defender(p, socar())
        }
    }

    private fun raios(): Float {
        print("$nome usou ataque de raios!")
        return ATAQUE_RAIOS
    }
}