public class Item {
        private int menuId;
        private int customerId;
        private String name;
        private String category;
        private int quantity;
        private float price;
        private String branch;
        private String description;
        public Item(int menuId, int customerId, String name, String category, float price, int quantity, String branch, String description) {
            super();
            this.menuId = menuId;
            this.customerId = customerId;
            this.name = name;
            this.category = category;
            this.price = price;
            this.branch = branch;
            this.quantity = quantity;
            this.description = description;
        }
        public int getMenuId(){
            return menuId;
        }
    
        public void setMenuId(int menuId){
            this.menuId = menuId;
        }
        public int getCustomerId(){
            return customerId;
        }
    
        public void setCustomerId(int customerId){
            this.customerId = customerId;
        }
        public String getName(){
            return name;
        }
        
        public void setName(String name){
            this.name = name;
        }
    
        public float getPrice() {
            return price;
        }
    
        public void setPrice(float price) {
            this.price = price;
        }
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity (int quantity){
            this.quantity = quantity;
        }
    
        public String getCategory(){
            return category;
        }
    
        public void setBranch ( String branch){
            this.branch = branch;
        }
    
        public String getBranch(){
            return branch;
        }
    
        public void setDescription( String description){
            this.description = description;
        }
    
        public String getDescription(){
            return description;
        }

        public boolean isOrdered() {
        return menuId != -1;
        }

        public void assign(int menuId) {
        this.menuId = menuId;
        }

        public void removeOrderedItem() {
        this.menuId = -1;
        }
}
