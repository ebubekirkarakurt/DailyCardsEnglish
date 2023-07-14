package com.ekasoftware.english.view.chatbot.data

data class QABotData(
    val merhaba: String = "",
    val nasilsiniz: String = "",
    val havaNasil: String = ""
) {
    companion object {
        fun getDefault(): QABotData {
            return QABotData(
                merhaba = "Merhaba! Nasıl yardımcı olabilirim?",
                nasilsiniz ="Ben bir botum, iyi olduğumu söyleyemem ama size nasıl yardımcı olabilirim?",
                havaNasil = "Ben bir bot olduğum için hava durumunu hissedemem, ama size hava durumu servislerini önerebilirim."
            )
        }
    }
}