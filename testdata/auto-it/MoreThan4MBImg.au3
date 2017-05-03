WinWaitActive("File Upload")
Send("C:\vibe\testdata\photos\MoreThan4MbImg.jpg")
Sleep(2000)
Send("{tab}")
Sleep(500)
Send("{tab}")
Sleep(1000)
Send("{ENTER}")
WinClose("File Upload")


