WinWaitActive("File Upload")
Sleep(2000)
Send("C:\vibe\testdata\photos\healthy-drinks.jpg")
Sleep(2000)
Send("{tab}")
Sleep(1000)
Send("{tab}")
Sleep(1000)
Send("{ENTER}")
WinClose("File Upload")


