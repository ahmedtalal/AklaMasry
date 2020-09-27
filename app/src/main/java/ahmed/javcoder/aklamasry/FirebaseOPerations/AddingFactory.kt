package ahmed.javcoder.aklamasry.FirebaseOPerations

class AddingFactory {
    companion object {
        fun addFactory(add:AddingInterface) {
            add.checkViews()
            add.addOPeration()
        }
    }
}