import java.io.*;
import java.util.Scanner;

public class CPU_Emulator {

    public static int countLines(String filename) throws IOException {

        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename));
        try {
            lineNumberReader.skip(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int lines = lineNumberReader.getLineNumber();
        lineNumberReader.close();
        return lines + 1;
    }

    public static void getCodes(int[] line, String[] instruction, int[] value, String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int valueN;
        int i = 0;
        while (input.hasNext()) {
            int lineN = input.nextInt();
            line[i] = lineN;
            String instructionN = input.next();
            instruction[i] = instructionN;
            if (instructionN.equals("START") || instructionN.equals("DISP") || instructionN.equals("HALT")) {
                valueN = 0;
            } else {
                valueN = input.nextInt();
            }
            value[i] = valueN;
            i++;
        }
    }


    public static void main(String[] args) throws Exception {
        File codes = new File(args[0]);
        int first = countLines(args[0]);
        String[] instruction = new String[first];
        int[] line = new int[first];
        int[] value = new int[first];
        getCodes(line, instruction, value, args[0]);
        CPU cpu = new CPU();
        if (instruction[0].equals("START")== false) {
            throw new RuntimeException("You must start the CPU");
         }
            for (int i= cpu.PC;i<first-1;) {
                i= cpu.PC;
                String s=instruction[i];
                switch (s) {
                    case "LOAD":
                        cpu.LOAD(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "LOADM":
                        cpu.LOADM(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "STORE":
                        cpu.STORE(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "CMPM":
                        cpu.CMPM(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "CJMP":
                        cpu.CJMP(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "JMP":
                        cpu.JMP(value[cpu.PC]);
                        break;
                    case "ADD":
                        cpu.ADD(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "ADDM":
                        cpu.ADDM(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "SUBM":
                        cpu.SUBM(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "SUB":
                        cpu.SUB(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "MUL":
                        cpu.MUL(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "MULM":
                        cpu.MULM(value[cpu.PC]);
                        cpu.PC+=1;
                        break;
                    case "DISP":
                        cpu.DISP();
                        cpu.PC+=1;
                        break;
                    case "HALT":
                        cpu.HALT();
                        break;
                    default:
                }
            }


        }
    }



class CPU{
    int AC=0;
    int PC=1;
    int[] M = new int[256];
    byte f=0;
    String[] instruction;
    int[] line;
    int[] value;

    public void LOAD(int i){
        AC=i;
    }
    public void LOADM(int i){
        AC=M[i];
    }
    public void STORE(int i){
        M[i]=AC;
    }
    public void CMPM(int i){
        if (AC>M[i]){
            f=1;
        }
        else if (AC == M[i]){
            f=0;
        }
        else if (AC<M[i]){
            f=-1;
        }
    }
    public void CJMP(int i){
        if (f>0){
            PC=i-1;
        }
        else {}
    }
    public void JMP(int i){
        PC=i;
    }
    public void ADD(int i){
        AC=AC+i;
    }
    public void ADDM(int i){
        AC=AC+M[i];
    }
    public void SUBM(int i){
        AC=AC-M[i];
    }
    public void SUB(int i){
        AC=AC+i;
    }
    public void MUL(int i){
        AC=AC*4;
    }
    public void MULM(int i){
        AC=AC*M[i];
    }
    public void DISP(){
        System.out.println(AC);
        System.out.println(M[200]);
        System.out.println(M[201]);
        System.out.println(M[202]);
    }
    public void HALT(){

    }


}
