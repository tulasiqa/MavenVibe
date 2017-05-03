WinWaitActive("File Upload")
Send("C:\vibe\AutoIt\event2.jpg")
Sleep(2000)
Send("{tab}")
Sleep(500)
Send("{tab}")
Sleep(500)
Send("{ENTER}")
WinClose("File Upload")


