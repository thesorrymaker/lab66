package Lab;
import CSV.CSVReader;
import CSV.CSVWriter;
import Collection.*;
import Command.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main (String [] args) throws Exception{
        //initialize collection of people
        new CollectionsofVehicle().doInitialization();
        CommandManager manager = new CommandManager();
        //int port = Integer.parseInt(args[0]);
        int port = 5555;
        DatagramSocket datagramSocket = new DatagramSocket(9999);

        //make sure every request of initialization just be answered one time
        LinkedHashSet<Integer> ports = new LinkedHashSet<>();
        while (true) {
            //receive
            byte[] buffer = new byte[102400];
            DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
            datagramSocket.receive(datagramPacket);
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(datagramPacket.getData()));
            //main part
            try {
                //deal with different command
                CommandPackage commandPackage = (CommandPackage) inputStream.readObject();
                if (commandPackage != null) {
                    //first load file
                    if(commandPackage.isSet()&!ports.contains(datagramPacket.getPort())){
                        ports.add(datagramPacket.getPort());
                        LinkedHashSet<Vehicle> vehicles = new LinkedHashSet<>();
                        File F = new File(commandPackage.getFileName());
                        if(!F.exists()){
                            F.createNewFile();
                            new CSVWriter().writetofile(vehicles,commandPackage.getFileName());
                        }else {
                            vehicles = new CSVReader().readfile(vehicles,commandPackage.getFileName());
                            for (Vehicle P:vehicles){
                                if(P.getId()>Vehicle.getidcode()){
                                    Vehicle.setIdcode(P.getId());
                                }
                            }
                        }
                        System.out.printf("The port of client is %d\n",datagramPacket.getPort());
                        CollectionsofVehicle.setVehicle(vehicles);
                        manager.setOut("Your collection is synchronized by file\n",false);
                        Response response = new Response(vehicles,manager.getOut());
                        ByteArrayOutputStream BAO = new ByteArrayOutputStream();
                        ObjectOutputStream OOS = new ObjectOutputStream(BAO);
                        OOS.writeObject(response);
                        //another possibility
                        //DatagramPacket send = new DatagramPacket(BAO.toByteArray(),0,BAO.toByteArray().length,datagramPacket.getSocketAddress());
                        DatagramPacket send = new DatagramPacket(BAO.toByteArray(),0,BAO.toByteArray().length);
                        datagramSocket.connect(datagramPacket.getSocketAddress());
                        datagramSocket.send(send);
                        System.out.print(manager.getOut());
                        System.out.print("\n");
                        OOS.close();
                        //for commands except executescript
                    } else if (commandPackage.getList()==null&!commandPackage.isSet()) {
                        AbstractCommand command = commandPackage.getAbstractCommand();
                        new History().getHistory().add(command.getName() + "\n");
                        if (command.getName().equalsIgnoreCase("exit")) {
                            command.execute(manager,commandPackage);
                            Response response = new Response(Vehicle.getidcode(),manager.getOut());
                            ByteArrayOutputStream bao = new ByteArrayOutputStream();
                            ObjectOutputStream bos = new ObjectOutputStream(bao);
                            bos.writeObject(response);
                            byte [] data = bao.toByteArray();//manager.getOut().getBytes();
                            datagramSocket.send(new DatagramPacket(data, data.length, datagramPacket.getSocketAddress()));
                            new Save().execute(manager, commandPackage);
                            bos.close();
                            System.exit(2);
                        } else {
                            command.execute(manager, commandPackage);
                            if (command.getName().equalsIgnoreCase("updateid")) {
                                CollectionsofVehicle.setVehicle(sort(new CollectionsofVehicle().getVehicle()));
                            }
                        }
                        Response response = new Response(Vehicle.getidcode(),manager.getOut());
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        ObjectOutputStream bos = new ObjectOutputStream(bao);
                        bos.writeObject(response);
                        byte [] data = bao.toByteArray();//manager.getOut().getBytes();
                        DatagramPacket send = new DatagramPacket(data,data.length,datagramPacket.getSocketAddress());
                        datagramSocket.send(send);
                        System.out.print(manager.getOut());
                        System.out.print("\n");
                        bos.close();
                        //for command executescript
                    }else if(!commandPackage.isSet()){
                        StringBuilder S = new StringBuilder();
                        List<CommandPackage> list = commandPackage.getList();
                        Iterator<CommandPackage> iterator = list.iterator();
                        while(iterator.hasNext()){
                            CommandPackage used = iterator.next();
                            AbstractCommand command = used.getAbstractCommand();
                            new History().getHistory().add(command.getName()+"\n");
                            command.execute(manager,used);
                            S.append(manager.getOut()).append("\n");
                        }
                        manager.setOut(S.toString(),false);
                        Response response = new Response(Vehicle.getidcode(),manager.getOut());
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        ObjectOutputStream bos = new ObjectOutputStream(bao);
                        bos.writeObject(response);
                        byte [] data = bao.toByteArray();//manager.getOut().getBytes();
                        DatagramPacket send = new DatagramPacket(data,data.length,datagramPacket.getSocketAddress());
                        datagramSocket.send(send);
                        System.out.print(manager.getOut());
                        System.out.print("\n");
                        bos.close();
                    }
                }
            }catch (ClassNotFoundException|ParaInapproException|NullException C) {
                manager.setOut(C.getMessage(),false);
            }
        }
    }

    private static LinkedHashSet<Vehicle> sort(LinkedHashSet<Vehicle> linkedHashSet) throws NullException{
        LinkedHashSet<Vehicle> newone = new LinkedHashSet<>();
        Comparator<Vehicle> comparator;
        comparator = Comparator.comparingInt(a -> a.getId());
        linkedHashSet.stream().sorted(comparator).forEach(newone::add);
        return newone;
    }
}
