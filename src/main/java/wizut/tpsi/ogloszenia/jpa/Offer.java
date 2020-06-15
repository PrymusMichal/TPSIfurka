/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wizut.tpsi.ogloszenia.jpa;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pm41486
 */
@Entity
@Table(name = "offer")
public class Offer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 255, min = 5)
    @Column(name = "title")
    @NotNull
    private String title;

    @Min(1900)
    @NotNull
    @Column(name = "year")
    private Integer year;

    @Min(0)
    @NotNull
    @Column(name = "mileage")
    private Integer mileage;

    @Min(0)
    @Column(name = "engine_size")
    private BigDecimal engineSize;

    @Min(0)
    @Column(name = "engine_power")
    private Integer enginePower;

    @Min(1)
    @Max(5)
    @NotNull
    @Column(name = "doors")
    private Integer doors;

    @NotNull
    @Size(max = 30, min = 3)
    @Column(name = "colour")
    private String colour;

    @NotNull
    @Lob
    @Size(max = 65535, min = 5)
    @Column(name = "description")
    private String description;

    @Min(0)
    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @ManyToOne
    private CarModel model;

    @NotNull
    @JoinColumn(name = "body_style_id", referencedColumnName = "id")
    @ManyToOne
    private BodyStyle bodyStyle;

    @NotNull
    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    @ManyToOne
    private FuelType fuelType;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return the mileage
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the engineSize
     */
    public BigDecimal getEngineSize() {
        return engineSize;
    }

    /**
     * @param engineSize the engineSize to set
     */
    public void setEngineSize(BigDecimal engineSize) {
        this.engineSize = engineSize;
    }

    /**
     * @return the enginePower
     */
    public Integer getEnginePower() {
        return enginePower;
    }

    /**
     * @param enginePower the enginePower to set
     */
    public void setEnginePower(Integer enginePower) {
        this.enginePower = enginePower;
    }

    /**
     * @return the doors
     */
    public Integer getDoors() {
        return doors;
    }

    /**
     * @param doors the doors to set
     */
    public void setDoors(Integer doors) {
        this.doors = doors;
    }

    /**
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return the model
     */
    public CarModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(CarModel model) {
        this.model = model;
    }

    /**
     * @return the bodyStyle
     */
    public BodyStyle getBodyStyle() {
        return bodyStyle;
    }

    /**
     * @param bodyStyle the bodyStyle to set
     */
    public void setBodyStyle(BodyStyle bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    /**
     * @return the fuelType
     */
    public FuelType getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}
