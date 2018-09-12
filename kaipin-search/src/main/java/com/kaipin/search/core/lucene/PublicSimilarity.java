package com.kaipin.search.core.lucene;

import java.io.IOException;

import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.FieldInvertState;
import org.apache.lucene.index.Norm;
import org.apache.lucene.search.CollectionStatistics;
import org.apache.lucene.search.TermStatistics;
import org.apache.lucene.search.similarities.DefaultSimilarity;
import org.apache.lucene.search.similarities.Similarity;



public class PublicSimilarity extends DefaultSimilarity {

    /**
     * Implemented as <code>state.getBoost()*lengthNorm(numTerms)</code>, where
     * <code>numTerms</code> is {@link FieldInvertState#getLength()} if {@link
     #setDiscountOverlaps}
     * is false, else it's {@link FieldInvertState#getLength()} -
     * {@link FieldInvertState#getNumOverlap()}.
     *
     * <p>
     * <b>WARNING</b>: This API is new and experimental, and may suddenly change.
     * </p>
     */
    
     public float computeNorm(String field, FieldInvertState state) {
     final int numTerms;
     if (discountOverlaps)
     numTerms = state.getLength() - state.getNumOverlap();
     else
     numTerms = state.getLength();
     return (state.getBoost() * lengthNorm(field, numTerms));
     }
    
     /** Implemented as <code>1/sqrt(numTerms)</code>. */
    
     public float lengthNorm(String fieldName, int numTerms) {
     // System.out.println("fieldName:" + fieldName + "\tnumTerms:" + numTerms);
     // return (float)(1.0 / Math.sqrt(numTerms));
     return 1.0f;
     }
    
     /** Implemented as <code>1/sqrt(sumOfSquaredWeights)</code>. */
     @Override
     public float queryNorm(float sumOfSquaredWeights) {
     // return (float)(1.0 / Math.sqrt(sumOfSquaredWeights));\
     return 1.0f;
     }
    
     /** Implemented as <code>sqrt(freq)</code>. */
     // term freq 表示 term 在一个document的出现次数,这里设置为1.0f表示不考滤这个因素影响
     @Override
     public float tf(float freq) {
     return 1.0f;
    
     }
    
     /** Implemented as <code>1 / (distance + 1)</code>. */
     // 这里表示匹配的 term 与 term之间的距离因素,同样也不应该受影响
     @Override
     public float sloppyFreq(int distance) {
     return 1.0f;
     }
    
     /** Implemented as <code>log(numDocs/(docFreq+1)) + 1</code>. */
     // 这里表示匹配的docuemnt在全部document的影响因素,同理也不考滤
    
     public float idf(int docFreq, int numDocs) {
     return 1.0f;
     }
    
     /** Implemented as <code>overlap / maxOverlap</code>. */
     // 这里表示每一个Document中所有匹配的关键字与当前关键字的匹配比例因素影响,同理也不考滤.
     @Override
     public float coord(int overlap, int maxOverlap) {
     return 1.0f;
     }
    
     // Default false
     protected boolean discountOverlaps;
    
     /**
     * Determines whether overlap tokens (Tokens with 0 position increment) are ignored when
     * computing norm. By default this is false, meaning overlap tokens are counted just like
     * non-overlap tokens.
     *
     * <p>
     * <b>WARNING</b>: This API is new and experimental, and may suddenly change.
     * </p>
     *
     * @see #computeNorm
     */
     public void setDiscountOverlaps(boolean v) {
     discountOverlaps = v;
     }
    
     /**
     * @see #setDiscountOverlaps
     */
     public boolean getDiscountOverlaps() {
     return discountOverlaps;
     }


//    
//    @Override
//    public float tf(float freq) {
//
//    return 1.0f;
//
//    }
//
//
//    @Override
//
//    public float idf(long docFreq,long numDocs) {
//
//    return 1.0f;
//
//    }
//    public float lengthNorm(FieldInvertState state) {
//
//        final int numTerms;
//
//        if (discountOverlaps)
//        {
//        numTerms =state.getLength() -state.getNumOverlap();
//        }
//        else
//        {
//        numTerms =state.getLength();
//
//       
//        }
//        return state.getBoost() * ((float) (1.0 / numTerms));
//        }
//    
}
