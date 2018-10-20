#include <FileConstants.au3>
#include <MsgBoxConstants.au3>

Act1()

; The macro that keeps checking if a new command is received by the Java Program
Func Act1()

   ; Give the same path you have mentioned in ActionPrinter.java
   Local Const $sFilePath = "TestFile.txt"

   Dim $LineNum = 1
   Dim $PrevLineNum = 1
   Dim $initialized = 0

    Local $hFileOpen = FileOpen($sFilePath, $FO_READ)
    If $hFileOpen = -1 Then
        MsgBox($MB_SYSTEMMODAL, "", "An error occurred when reading the file.")
        Return False
    EndIf

    While True
	   ;Local $sFileRead = FileReadLine($hFileOpen, -1)
	   FileReadLine($hFileOpen, $LineNum+1)
	   If  @error = -1  Then
		  If $initialized = 0 Then
			 $PrevLineNum = $LineNum
			 $initialized =1
			 ContinueLoop
		  EndIf

		  If $PrevLineNum <> $LineNum Then
			Local $sFileRead = FileReadLine($hFileOpen, $LineNum)
			;ConsoleWrite("Last line of the file: " & $sFileRead & @CRLF )
			;MsgBox($MB_SYSTEMMODAL, "", "Last line of the file:" & @CRLF & $sFileRead)
			$PrevLineNum = $LineNum

			$aLocal = StringSplit($sFileRead,'{')
			ConsoleWrite("No of Splits:" & $aLocal[0] & " Win Name:" & $aLocal[1] & " Content:" & $aLocal[2] & @CRLF  )

			Act2($aLocal[1],$aLocal[2])

		 EndIf
		 Sleep(200)
	   Else
		 $LineNum = $LineNum+1
	  EndIf
   WEnd

EndFunc

; A macro that actually activates a given window, types the message and then sends it.
Func Act2($winName,$message)

 If(WinActive(WinActivate("[REGEXPTITLE:(?i)(" & $winName & " \[started\:.*)]"))) Then
 Send($message)
 Send("{ENTER}")
 EndIf
EndFunc
