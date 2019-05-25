package com.mag.lab2.model.dto.xml;

import com.mag.lab2.model.dto.Machinist;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="machinists")
public class XmlMachinistListWrapper {

    private List<Machinist> machinists;

    public XmlMachinistListWrapper(List<Machinist> machinists) {
        this.machinists = machinists;
    }

    public XmlMachinistListWrapper() {
        this.machinists = new ArrayList<>();
    }

    @XmlElement(name="machinist")
    public List<Machinist> getMachinists() {
        return machinists;
    }
}
