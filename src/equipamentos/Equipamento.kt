package equipamentos

abstract class Equipamento() {
    var durabilidade: Float = 100f
    abstract val DESGASTE: Int

    fun desgastar() {
        if (durabilidade>DESGASTE) {
            durabilidade -= DESGASTE
        } else durabilidade = 0f
    }

    fun isUtil(): Boolean {
        return durabilidade>0
    }

}