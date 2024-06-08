<template>
  <ion-row>
    <div class="container">
      <div :class="{ slides: true, fade: true }" ref="slide">
        <div class="page">{{ `${slideIndex + 1} / ${images.length}` }}</div>
        <ion-img class="image" :src="images[slideIndex].src" />
        <div :class="descriptionClass">{{ images[slideIndex].alt }}</div>
      </div>

      <a class="prev" @click="plusSlides(-1)">&#10094;</a>
      <a class="next" @click="plusSlides(1)">&#10095;</a>
    </div>

    <div class="dot-container">
      <span v-for="(image, index) in images"
            :key="image.src"
            :class="{ dot: true, active: index === slideIndex}"
            @click="currentSlide(index)"></span>
    </div>
  </ion-row>
</template>

<script lang="ts">
export type SlideImageData = {
  src: string,
  alt: string
}

export default {
  props: {
    images: {
      type: Array<SlideImageData>,
      required: true
    }
  },
  data() {
    return {
      slideIndex: 0
    }
  },
  computed: {
    descriptionClass() {
      return {
        description: true,
        hidden: this.images[this.slideIndex].alt.length <= 0
      }
    }
  },
  methods: {
    plusSlides(amount: number) {
      const newAmount = (this.slideIndex + amount) % this.images.length;
      this.setSlideIndex(newAmount >= 0 ?
          newAmount :
          this.images.length + newAmount);
    },
    currentSlide(index: number) {
      this.setSlideIndex(index);
    },
    setSlideIndex(index: number) {
      if (this.slideIndex === index) {
        return;
      }

      const slide = this.$refs.slide as HTMLDivElement;
      slide.style.animation = "none";
      this.slideIndex = index;
      setTimeout(() => slide.style.animation = "", 0);
    }
  }
}
</script>

<style scoped lang="scss">
ion-row {
  flex-direction: column;
}

.image {
  width: 100%;
}

.container {
  position: relative;
  width: 100%;
  margin: auto;

  .page {
    background-color: rgba(0, 0, 0, 0.6);
    color: #f2f2f2;
    font-size: 12px;
    padding: 8px 12px;
    position: absolute;
    top: 0;
  }

  .slides {
    display: flex;
    justify-content: center;
    text-align: center;
  }

  .description {
    background-color: rgba(0, 0, 0, 0.6);
    color: #f2f2f2;
    font-size: 15px;
    padding: 8px 12px;
    position: absolute;
    bottom: 8px;
    width: 100%;
    text-align: center;

    &.hidden {
      display: none;
    }
  }

  .prev, .next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    margin-top: -22px;
    padding: 16px;
    color: white;
    font-weight: bold;
    font-size: 18px;
    transition: 0.6s ease;
    border-radius: 0 3px 3px 0;
    user-select: none;

    &:hover {
      background-color: rgba(0,0,0,0.8);
    }
  }

  .next {
    right: 0;
    border-radius: 3px 0 0 3px;
  }

  .dot-container {
    text-align: center;
    margin-top: 10px;

    .dot {
      display: inline-block;
      cursor: pointer;
      height: 0.8rem;
      width: 0.8rem;
      margin: 0 2px;
      background-color: #bbb;
      border-radius: 50%;
      transition: background-color 0.6s ease;

      &:hover, &.active {
        background-color: #717171;
      }
    }
  }
}

.fade {
  animation: fade 1.5s;
}

@keyframes fade {
  from {
    opacity: 0
  }
  to {
    opacity: 1
  }
}
</style>