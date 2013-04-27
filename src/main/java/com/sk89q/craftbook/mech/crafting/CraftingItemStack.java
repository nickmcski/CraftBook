package com.sk89q.craftbook.mech.crafting;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import com.sk89q.craftbook.util.ItemUtil;

/**
 * @author Silthus
 */
public class CraftingItemStack implements Comparable<CraftingItemStack> {

    private ItemStack item;

    //Advanced data
    private HashMap<String, Object> advancedData = new HashMap<String, Object>();

    public HashMap<String, Object> getAllAdvancedData() {

        return advancedData;
    }

    public boolean hasAdvancedData() {
        return !advancedData.isEmpty();
    }

    public boolean hasAdvancedData(String key) {
        return advancedData.containsKey(key);
    }

    public Object getAdvancedData(String key) {
        return advancedData.get(key);
    }

    public void addAdvancedData(String key, Object data) {
        Bukkit.getLogger().info("Adding advanced data of type: " + key + " to an ItemStack!");
        advancedData.put(key, data);
    }

    public CraftingItemStack(ItemStack item) {

        this.item = item;
    }

    public ItemStack getItemStack() {

        return item;
    }

    public CraftingItemStack add(CraftingItemStack stack) {

        if (stack.isSameType(this)) {
            ItemUtil.addToStack(item, stack.getItemStack());
            advancedData.putAll(stack.getAllAdvancedData());
        }
        return this;
    }

    public boolean isSameType(CraftingItemStack stack) {

        if (getItemStack().getData().getData() == -1 || stack.getItemStack().getData().getData() == -1)
            return stack.getItemStack().getTypeId() == getItemStack().getTypeId();
        return stack.getItemStack().getTypeId() == getItemStack().getTypeId() && stack.getItemStack().getData() == getItemStack().getData();
    }

    @Override
    public int compareTo(CraftingItemStack stack) {

        if (stack.getItemStack().getAmount() > item.getAmount()) return 1;
        if (stack.getItemStack().getAmount() == item.getAmount()) return 0;
        return -1;
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + item.hashCode();
        result = prime * result + advancedData.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof CraftingItemStack) {
            CraftingItemStack stack = (CraftingItemStack) obj;
            return isSameType(stack) && stack.getItemStack().getAmount() == getItemStack().getAmount();
        }
        return false;
    }
}
