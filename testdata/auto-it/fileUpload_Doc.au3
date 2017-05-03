WinWaitActive("File Upload")
Send("C:\vibe\testdata\files\icentris_doc.doc")
Sleep(2000)
Send("{tab}")
Sleep(500)
Send("{tab}")
Sleep(500)
Send("{ENTER}")
WinClose("File Upload")


