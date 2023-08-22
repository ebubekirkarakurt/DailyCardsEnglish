package com.ekasoftware.english.view.chatbot.data

data class QABotData(
    val merhaba: String = "",
    val ingilizceTemel: String = "",
    val listening: String = "",
    val vocablary: String = ""

) {
    companion object {
        fun getDefault(): QABotData {
            return QABotData(
                merhaba = "Merhaba! Ben OLAbot. Size nasıl yardımcı olabilirim?",
                ingilizceTemel ="İngilizce 3 temelden oluşur ve bunlar;" +
                        "Okuma: İngilizce öğrenirken ilk adım, çeşitli metinleri okuma yeteneğinizi geliştirmektir.\n" +
                        "Yazma: Kendi düşüncelerinizi ve fikirlerinizi İngilizce olarak ifade edebilmek önemli bir beceridir.\n" +
                        "Dinleme: İngilizce konuşulan içerikleri dinlemek, dilin seslerini ve konuşma ritmini anlamınıza yardımcı olur.",
                listening = "Nglish uygulamasında bulunan okuma parçalarını günlük okuyarak okuma becerinizi geliştirebilir aynı zamanda okuma parçalarını dinleyerek dinleme becerinizi de geliştirebilirsiniz. " +
                        "Neden İngilizce öğrenmek istediğinizi hatırlayarak hedeflerinize odaklanmayı unutmayın. Hoşça kalın ve öğrenmeye devam edin! ",
                vocablary = "Kelime dağarcığınızı genişletmek için Nglish uygulmasında bulunan kelime listelerinden" +
                        "bilmediğiniz kelimeleri belirledikten sonra günlük 20 kelime ezberleyerek kelime bilginizi geliştirebilirsiniz." +
                        "Neden İngilizce öğrenmek istediğinizi hatırlayarak hedeflerinize odaklanmayı unutmayın. Hoşça kalın ve öğrenmeye devam edin!  "
            )
        }
    }
}