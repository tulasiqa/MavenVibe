WinWaitActive("File Upload")
Send("C:\vibe\AutoIt\anniversar.jpg")
Sleep(2000)
Send("{tab}")
Sleep(500)
Send("{tab}")
Sleep(500)
Send("{ENTER}")
WinClose("File Upload")


