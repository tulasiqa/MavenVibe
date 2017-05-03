WinWaitActive("File Upload")
Send("C:\vibe\testdata\files\icentris_text.txt")
Sleep(4000)
Send("{tab}")
Sleep(300)
Send("{tab}")
Sleep(300)
Send("{ENTER}")
Sleep(300)
WinClose("File Upload")


