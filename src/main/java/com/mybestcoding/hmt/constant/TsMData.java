package com.mybestcoding.hmt.constant;

import com.redislabs.redistimeseries.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: lixinkai
 * @description: Ts 过滤后的数据
 * @date: 2021/3/4 14:47
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TsMData implements Serializable {

    private static final long serialVersionUID = 7907470864030745722L;
    private String key;

    private Map<String, String> labels;

    private List<TsData> results;

    /**
     * 将 {@link Range} 转换为自定义的 {@link TsMData}
     *
     * @param mResults Range 对象
     * @return TsMData 对象
     */
    public static TsMData mResultMapToTsMData(Range mResults) {
        List<TsData> results = Arrays.stream(mResults.getValues())
                .map(TsData::mapToTsData)
                .collect(Collectors.toList());
        return new TsMData(mResults.getKey(), mResults.getLabels(), results);
    }
}
