package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class MenuRepository implements IMenuRepository{
    private static final String filename = "data/menu.txt";
    private List<MenuDataModel> listMenu;

    public MenuRepository(){
    }

    private void readMenu() {
        ClassLoader classLoader = MenuRepository.class.getClassLoader();
        try {
            File file = new File(URLDecoder.decode(Objects.requireNonNull(classLoader.getResource(filename)).getFile(), "UTF-8"));
            this.listMenu = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    MenuDataModel menuItem = getMenuItem(line);
                    listMenu.add(menuItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MenuDataModel getMenuItem(String line){
        MenuDataModel item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price);
        return item;
    }

    @Override
    public List<MenuDataModel> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }

}
