Set objFso = CreateObject("Scripting.FileSystemObject")
Set Folder = objFSO.GetFolder("C:\Users\Ajay\Downloads\eclipse-jee-juno-SR1-win32-x86_64\projects\Saros\Game\src\ajaycamden\res\mapsections\")

For Each File In Folder.Files
    sNewFile = File.Name
    sNewFile = Replace(sNewFile," [www.imagesplitter.net]-","")
    if (sNewFile<>File.Name) then 
        File.Move(File.ParentFolder+"\"+sNewFile)
    end if

Next