WinWaitActive("File Upload")
Send("c:\vibe\testdata\files\icentris_ppt.pptx")
Sleep(4000)
Send("{tab}")
Sleep(300)
Send("{tab}")
Sleep(300)
Send("{ENTER}")
Sleep(300)
WinClose("File Upload")


